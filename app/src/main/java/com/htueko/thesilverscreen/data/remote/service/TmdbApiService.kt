package com.htueko.thesilverscreen.data.remote.service

import com.htueko.thesilverscreen.data.remote.OptionalConstant
import com.htueko.thesilverscreen.data.remote.RelativeConstant
import com.htueko.thesilverscreen.data.remote.RemoteConstant
import com.htueko.thesilverscreen.data.remote.RequireConstant
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import javax.inject.Inject
import javax.inject.Singleton

/**
 * implementation of api service
 * @param httpClient - ktor android client
 * @see [HttpClient]
 * @see [ApiHelper]
 */
@Singleton
class TmdbApiService @Inject constructor(
    private val httpClient: HttpClient
) : ApiHelper {

    override suspend fun getUpcomingMovies(page: Int): ResultOf<MovieDto> {
        return try {
            val response = httpClient.get<HttpResponse>(path = RelativeConstant.upcoming) {
                parameter(RequireConstant.apiKey, RemoteConstant.tmdbApiKey)
                parameter(OptionalConstant.page, page)
            }
            if (response.status.isSuccess()) {
                ResultOf.Success(response.receive())
            } else {
                ResultOf.ApiError(response.status.description)
            }
        } catch (e: Exception) {
            ResultOf.NetworkError(e)
        }
    }

    override suspend fun getTopRatedMovies(page: Int): ResultOf<MovieDto> {
        return try {
            val response = httpClient.get<HttpResponse>(path = RelativeConstant.topRated) {
                parameter(RequireConstant.apiKey, RemoteConstant.tmdbApiKey)
                parameter(OptionalConstant.page, page)
            }
            if (response.status.isSuccess()) {
                ResultOf.Success(response.receive())
            } else {
                ResultOf.ApiError(response.status.description)
            }
        } catch (e: Exception) {
            ResultOf.NetworkError(e)
        }
    }

    override suspend fun getPopularMovies(page: Int): ResultOf<MovieDto> {
        return try {
            val response = httpClient.get<HttpResponse>(path = RelativeConstant.popular) {
                parameter(RequireConstant.apiKey, RemoteConstant.tmdbApiKey)
                parameter(OptionalConstant.page, page)
            }
            if (response.status.isSuccess()) {
                ResultOf.Success(response.receive())
            } else {
                ResultOf.ApiError(response.status.description)
            }
        } catch (e: Exception) {
            ResultOf.NetworkError(e)
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): ResultOf<MovieDto> {
        return try {
            val response = httpClient.get<HttpResponse>(path = RelativeConstant.nowPlaying) {
                parameter(RequireConstant.apiKey, RemoteConstant.tmdbApiKey)
                parameter(OptionalConstant.page, page)
            }
            if (response.status.isSuccess()) {
                ResultOf.Success(response.receive())
            } else {
                ResultOf.ApiError(response.status.description)
            }
        } catch (e: Exception) {
            ResultOf.NetworkError(e)
        }
    }

    override suspend fun getMovieDetailById(movieId: Int): ResultOf<MovieDetailDto> {
        return try {
            val response =
                httpClient.get<HttpResponse>(path = RelativeConstant.detail.plus("$movieId")) {
                    parameter(RequireConstant.apiKey, RemoteConstant.tmdbApiKey)
                }
            if (response.status.isSuccess()) {
                ResultOf.Success(response.receive())
            } else {
                ResultOf.ApiError(response.status.description)
            }
        } catch (e: Exception) {
            ResultOf.NetworkError(e)
        }
    }
}
