package com.htueko.thesilverscreen.data.remote.service

import com.htueko.thesilverscreen.data.remote.dto.MovieDetailDto
import com.htueko.thesilverscreen.data.remote.dto.MovieDto
import com.htueko.thesilverscreen.domain.model.status.ResultOf

/**
 * separation of concern for network operation
 * @see [TmdbApiService]
 */
interface ApiHelper {
    /**
     * to get the upcoming movies
     * @param page - page count for request
     * @return [MovieDto]
     */
    suspend fun getUpcomingMovies(page: Int): ResultOf<MovieDto>

    /**
     * to get the top rated movies
     * @param page - page count for request
     * @return [MovieDto]
     */
    suspend fun getTopRatedMovies(page: Int): ResultOf<MovieDto>

    /**
     * to get the popular movies
     * @param page - page count for request
     * @return [MovieDto]
     */
    suspend fun getPopularMovies(page: Int): ResultOf<MovieDto>

    /**
     * to get now playing movies
     * @param page - page count for request
     * @return [MovieDto]
     */
    suspend fun getNowPlayingMovies(page: Int): ResultOf<MovieDto>

    /**
     * to get the detail of the movie by id
     * @param movieId - id of type integer for movie detail
     * @return [MovieDetailDto]
     */
    suspend fun getMovieDetailById(movieId: Int): ResultOf<MovieDetailDto>
}
