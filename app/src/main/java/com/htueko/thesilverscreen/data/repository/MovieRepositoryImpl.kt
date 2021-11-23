package com.htueko.thesilverscreen.data.repository

import com.htueko.thesilverscreen.data.remote.mapper.RemoteMapper
import com.htueko.thesilverscreen.data.remote.service.ApiHelper
import com.htueko.thesilverscreen.domain.model.Movie
import com.htueko.thesilverscreen.domain.model.MovieDetail
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import com.htueko.thesilverscreen.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * to get the response from remote api service and map to desire model then return the result
 * @param [apiService] - remote operation
 * @param [remoteMapper] - to map the response dto to model
 * @see [ApiHelper]
 * @see [RemoteMapper]
 * @see [Movie]
 * @see [MovieDetail]
 */
class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiHelper,
    private val remoteMapper: RemoteMapper,
) : MovieRepository {

    override suspend fun getUpcomingMovies(page: Int): ResultOf<List<Movie>> {
        val response = apiService.getUpcomingMovies(page = page)
        val result = remoteMapper.mapMovie(response)
        return result
    }

    override suspend fun getTopRatedMovies(page: Int): ResultOf<List<Movie>> {
        val response = apiService.getTopRatedMovies(page = page)
        val result = remoteMapper.mapMovie(response)
        return result
    }

    override suspend fun getPopularMovies(page: Int): ResultOf<List<Movie>> {
        val response = apiService.getPopularMovies(page = page)
        val result = remoteMapper.mapMovie(response)
        return result
    }

    override suspend fun getNowPlayingMovies(page: Int): ResultOf<List<Movie>> {
        val response = apiService.getNowPlayingMovies(page = page)
        val result = remoteMapper.mapMovie(response)
        return result
    }

    override suspend fun getMovieDetailById(movieId: Int): ResultOf<MovieDetail> {
        val response = apiService.getMovieDetailById(movieId = movieId)
        val result = remoteMapper.mapMovieDetail(response)
        return result
    }
}
