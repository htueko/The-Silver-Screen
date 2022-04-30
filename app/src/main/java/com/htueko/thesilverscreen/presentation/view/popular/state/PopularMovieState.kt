@file:Suppress("MatchingDeclarationName")
package com.htueko.thesilverscreen.presentation.view.popular.state

import com.htueko.thesilverscreen.domain.model.Movie

data class PopularMoviesUiState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String = "",
    val movies: List<Movie> = emptyList(),
)
