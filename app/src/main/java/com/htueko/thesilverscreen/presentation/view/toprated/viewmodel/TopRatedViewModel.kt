package com.htueko.thesilverscreen.presentation.view.toprated.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.thesilverscreen.domain.model.Movie
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import com.htueko.thesilverscreen.domain.usecase.MovieUseCases
import com.htueko.thesilverscreen.presentation.view.toprated.state.TopRatedMoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    private val _uiState = mutableStateOf(TopRatedMoviesUiState())
    val uiState: State<TopRatedMoviesUiState> = _uiState

    init {
        getTopRatedMovies()
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            toggleLoading(true)
            when (val response = movieUseCases.getNowPlayingMovies(1)) {
                is ResultOf.ApiError -> {
                    toggleLoading(false)
                    toggleError(true)
                    processError(response.message)
                }
                is ResultOf.NetworkError -> {
                    toggleLoading(false)
                    toggleError(true)
                    processError(response.throwable.localizedMessage ?: "Unknown Error")
                }
                is ResultOf.Success -> {
                    toggleLoading(false)
                    toggleError(false)
                    processData(response.data)
                }
            }
        }
    }

    private fun toggleLoading(value: Boolean) {
        _uiState.value = TopRatedMoviesUiState(
            isLoading = value
        )
    }

    private fun toggleError(hasError: Boolean) {
        _uiState.value = TopRatedMoviesUiState(
            hasError = hasError
        )
    }

    private fun processError(errorMessage: String) {
        _uiState.value = TopRatedMoviesUiState(
            errorMessage = errorMessage
        )
    }

    private fun processData(data: List<Movie>) {
        _uiState.value = TopRatedMoviesUiState(
            movies = data
        )
    }
}
