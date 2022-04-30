@file:Suppress("MatchingDeclarationName")
package com.htueko.thesilverscreen.presentation.view.toprated.state

import com.htueko.thesilverscreen.domain.model.Movie

data class TopRatedMoviesUiState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String = "",
    val movies: List<Movie> = emptyList(),
)
