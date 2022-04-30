@file:Suppress("TooGenericExceptionCaught")
package com.htueko.thesilverscreen.data.remote.service

import com.htueko.thesilverscreen.data.remote.dto.MovieDetailDto
import com.htueko.thesilverscreen.data.remote.dto.MovieDto
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import javax.inject.Inject

/**
 * implementation of [ApiHelper] with [TmdbApiService]
 * @param [apiService] Remote Tmdb api service
 * @see [ApiHelper]
 */
class RemoteApiImpl @Inject constructor(
    private val apiService: TmdbApiService
) : ApiHelper {

    override suspend fun getUpcomingMovies(page: Int): ResultOf<MovieDto> {
        return try {
            val response = apiService.getUpcomingMovies(page = page)
            if (!response.isSuccessful) {
                ResultOf.ApiError(response.message())
            } else {
                if (response.body() == null) {
                    return ResultOf.ApiError(response.message())
                }
                ResultOf.Success(response.body()!!)
            }
        } catch (e: Exception) {
            ResultOf.NetworkError(e)
        }
    }

    override suspend fun getTopRatedMovies(page: Int): ResultOf<MovieDto> {
        return try {
            val response = apiService.getTopRatedMovies(page = page)
            if (!response.isSuccessful) {
                ResultOf.ApiError(response.message())
            } else {
                if (response.body() == null) {
                    return ResultOf.ApiError(response.message())
                }
                ResultOf.Success(response.body()!!)
            }
        } catch (e: Exception) {
            ResultOf.NetworkError(e)
        }
    }

    override suspend fun getPopularMovies(page: Int): ResultOf<MovieDto> {
        return try {
            val response = apiService.getPopularMovies(page = page)
            if (!response.isSuccessful) {
                ResultOf.ApiError(response.message())
            } else {
                if (response.body() == null) {
                    return ResultOf.ApiError(response.message())
                }
                ResultOf.Success(response.body()!!)
            }
        } catch (e: Exception) {
            ResultOf.NetworkError(e)
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): ResultOf<MovieDto> {
        return try {
            val response = apiService.getNowPlayingMovies(page = page)
            if (!response.isSuccessful) {
                ResultOf.ApiError(response.message())
            } else {
                if (response.body() == null) {
                    return ResultOf.ApiError(response.message())
                }
                ResultOf.Success(response.body()!!)
            }
        } catch (e: Exception) {
            ResultOf.NetworkError(e)
        }
    }

    override suspend fun getMovieDetailById(movieId: Int): ResultOf<MovieDetailDto> {
        return try {
            val response = apiService.getMovieDetailById(movieId = movieId.toString())
            if (!response.isSuccessful) {
                ResultOf.ApiError(response.message())
            } else {
                if (response.body() == null) {
                    return ResultOf.ApiError(response.message())
                }
                ResultOf.Success(response.body()!!)
            }
        } catch (e: Exception) {
            ResultOf.NetworkError(e)
        }
    }
}
