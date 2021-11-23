package com.htueko.thesilverscreen.domain.repository

import com.htueko.thesilverscreen.data.repository.MovieRepositoryImpl
import com.htueko.thesilverscreen.domain.model.Movie
import com.htueko.thesilverscreen.domain.model.MovieDetail
import com.htueko.thesilverscreen.domain.model.status.ResultOf

/**
 * separation of concern for repository pattern
 * @see [MovieRepositoryImpl]
 */
interface MovieRepository {

    /**
     * to get the upcoming movies
     * @param page - page count for request
     * @return list of [Movie]
     */
    suspend fun getUpcomingMovies(page: Int): ResultOf<List<Movie>>

    /**
     * to get the top rated movies
     * @param page - page count for request
     * @return list of [Movie]
     */
    suspend fun getTopRatedMovies(page: Int): ResultOf<List<Movie>>

    /**
     * to get the popular movies
     * @param page - page count for request
     * @return list of [Movie]
     */
    suspend fun getPopularMovies(page: Int): ResultOf<List<Movie>>

    /**
     * to get now playing movies
     * @param page - page count for request
     * @return list of [Movie]
     */
    suspend fun getNowPlayingMovies(page: Int): ResultOf<List<Movie>>

    /**
     * to get the detail of the movie by id
     * @param movieId - id of type integer for movie detail
     * @return [MovieDetail]
     */
    suspend fun getMovieDetailById(movieId: Int): ResultOf<MovieDetail>
}
