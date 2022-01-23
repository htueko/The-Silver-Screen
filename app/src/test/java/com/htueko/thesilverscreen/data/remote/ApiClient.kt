package com.htueko.thesilverscreen.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.get

class ApiClient(private val client: HttpClient) {

    suspend fun getNowPlayingMovies(page: Int): MovieDto {
        return client.get("/")
    }

    suspend fun getNowPlayingMoviesNotFound(page: Int) = client.get<HttpResponseData>("/")

    suspend fun getUpComingMovies(page: Int): MovieDto {
        return client.get("/")
    }

    suspend fun getTopRatedMovies(page: Int): MovieDto {
        return client.get("/")
    }
    suspend fun getPopularMovies(page: Int): MovieDto {
        return client.get("/")
    }

    suspend fun getMovieDetailById(movieId: Int): MovieDetailDto {
        return client.get("/")
    }
}
