package com.htueko.thesilverscreen.domain.usecase

/**
 * wrapper class for movie related interactors
 */
data class MovieUseCases(
    val getMovieDetailById: GetMovieDetailByIdUseCase,
    val getNowPlayingMovies: GetNowPlayingMoviesUseCase,
    val getPopularMovies: GetPopularMoviesUseCase,
    val getTopRatedMovies: GetTopRatedMoviesUseCase,
    val getUpComingMovies: GetUpcomingMoviesUseCase
)
