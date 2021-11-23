package com.htueko.thesilverscreen.presentation.view.upcoming.state

import com.htueko.thesilverscreen.domain.model.Movie

data class UpComingMovieState(
    val hasError: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList()
)
