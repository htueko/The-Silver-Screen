@file:Suppress("UnusedPrivateMember")
package com.htueko.thesilverscreen.presentation.view.nowplaying.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.thesilverscreen.domain.model.Movie
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import com.htueko.thesilverscreen.domain.usecase.MovieUseCases
import com.htueko.thesilverscreen.presentation.view.nowplaying.state.NowPlayingMoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    private val _uiState = mutableStateOf(NowPlayingMoviesUiState())
    val uiState: State<NowPlayingMoviesUiState> = _uiState

    private val currentPage = mutableStateOf(1)
    private var scrollPosition = 0

    init {
        getNowPlayingMovies(1)
    }

    private fun getNowPlayingMovies(page: Int) {
        viewModelScope.launch {
            toggleLoading(true)
            when (val response = movieUseCases.getNowPlayingMovies(page)) {
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
                    // fetchMoreMovies(response.data)
                    processData(response.data)
                }
            }
        }
    }

//    fun incrementPageNumber() {
//        currentPage.value = currentPage.value + 1
//    }
//
//    fun onChangeScrollPosition(position: Int) {
//        scrollPosition = position
//    }
//
//    private fun fetchMoreMovies(data: List<Movie>) {
//        val currentDataSet = ArrayList(uiState.value.movies)
//        currentDataSet.addAll(data)
//        processData(currentDataSet)
//    }
//
//    fun nextPage() {
//        viewModelScope.launch {
//            if ((scrollPosition + 1) >= (currentPage.value * 20)) {
//                toggleLoading(true)
//                incrementPageNumber()
//                delay(1_000)
//                if (currentPage.value > 1) {
//                    getNowPlayingMovies(currentPage.value)
//                }
//            }
//        }
//
//    }

    private fun toggleLoading(value: Boolean) {
        _uiState.value = NowPlayingMoviesUiState(
            isLoading = value
        )
    }

    private fun toggleError(hasError: Boolean) {
        _uiState.value = NowPlayingMoviesUiState(
            hasError = hasError
        )
    }

    private fun processError(errorMessage: String) {
        _uiState.value = NowPlayingMoviesUiState(
            errorMessage = errorMessage
        )
    }

    private fun processData(data: List<Movie>) {
        _uiState.value = NowPlayingMoviesUiState(
            movies = data
        )
    }
}
