package com.htueko.thesilverscreen.presentation.view.upcoming.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.thesilverscreen.domain.model.Movie
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import com.htueko.thesilverscreen.domain.usecase.MovieUseCases
import com.htueko.thesilverscreen.presentation.view.upcoming.state.UpComingMovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpComingViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    private val _uiState = mutableStateOf<UpComingMovieState>(UpComingMovieState())
    val uiState: State<UpComingMovieState> = _uiState

    init {
        getUpComingMovies()
    }

    private fun getUpComingMovies() {
        viewModelScope.launch {
            toggleLoading(true)
            when (val response = movieUseCases.getUpComingMovies(1)) {
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
        _uiState.value = UpComingMovieState(
            isLoading = value
        )
    }

    private fun toggleError(hasError: Boolean) {
        _uiState.value = UpComingMovieState(
            hasError = hasError
        )
    }

    private fun processError(errorMessage: String) {
        _uiState.value = UpComingMovieState(
            errorMessage = errorMessage
        )
    }

    private fun processData(data: List<Movie>) {
        _uiState.value = UpComingMovieState(
            movies = data
        )
    }
}
