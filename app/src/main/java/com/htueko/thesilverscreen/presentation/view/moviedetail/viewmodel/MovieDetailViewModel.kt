package com.htueko.thesilverscreen.presentation.view.moviedetail.viewmodel

import androidx.lifecycle.viewModelScope
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import com.htueko.thesilverscreen.domain.usecase.MovieUseCases
import com.htueko.thesilverscreen.presentation.base.mvi.BaseViewModel
import com.htueko.thesilverscreen.presentation.view.moviedetail.state.DetailSideEffect
import com.htueko.thesilverscreen.presentation.view.moviedetail.state.DetailUiEvent
import com.htueko.thesilverscreen.presentation.view.moviedetail.state.DetailUiState
import com.htueko.thesilverscreen.presentation.view.moviedetail.state.DetailUiStateInit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : BaseViewModel<DetailUiEvent, DetailUiState, DetailSideEffect>() {

    override fun createInitialState(): DetailUiState {
        return DetailUiStateInit().initialize()
    }

    override fun handleEvent(event: DetailUiEvent) {
        when (event) {
            is DetailUiEvent.LoadMovieDetail -> {
                getMovieDetail(event.id)
            }
        }
    }

    private fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            showLoading()
            when (val response = movieUseCases.getMovieDetailById(movieId)) {
                is ResultOf.ApiError -> {
                    setUiState {
                        copy(
                            hasError = true,
                            errorMessage = response.message
                        )
                    }
                }
                is ResultOf.NetworkError -> {
                    setUiState {
                        copy(
                            hasError = true,
                            errorMessage = response.throwable.localizedMessage
                                ?: "Unknow error occurred"
                        )
                    }
                }
                is ResultOf.Success -> {
                    setUiState {
                        copy(
                            movieDetail = response.data
                        )
                    }
                }
            }
        }
    }

    private fun showLoading() {
        setUiState { copy(loading = DetailUiState().loading) }
    }
}
