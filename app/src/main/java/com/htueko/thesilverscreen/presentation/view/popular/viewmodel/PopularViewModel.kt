package com.htueko.thesilverscreen.presentation.view.popular.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.thesilverscreen.domain.model.Movie
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import com.htueko.thesilverscreen.domain.usecase.MovieUseCases
import com.htueko.thesilverscreen.presentation.view.popular.state.PopularMoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    private val _uiState = mutableStateOf(PopularMoviesUiState())
    val uiState: State<PopularMoviesUiState> = _uiState

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
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
        _uiState.value = PopularMoviesUiState(
            isLoading = value
        )
    }

    private fun toggleError(hasError: Boolean) {
        _uiState.value = PopularMoviesUiState(
            hasError = hasError
        )
    }

    private fun processError(errorMessage: String) {
        _uiState.value = PopularMoviesUiState(
            errorMessage = errorMessage
        )
    }

    private fun processData(data: List<Movie>) {
        _uiState.value = PopularMoviesUiState(
            movies = data
        )
    }
}
