package com.htueko.thesilverscreen.data.remote.service

import com.htueko.thesilverscreen.data.remote.RelativeConstant
import com.htueko.thesilverscreen.data.remote.dto.MovieDetailDto
import com.htueko.thesilverscreen.data.remote.dto.MovieDto
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import io.ktor.client.HttpClient
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Tmdb api service
 */
interface TmdbApiService {

    @GET(RelativeConstant.upcoming + "{page}")
    suspend fun getUpcomingMovies(
        @Path("page") page: Int
    ): Response<MovieDto>

    @GET(RelativeConstant.topRated + "{page}")
    suspend fun getTopRatedMovies(
        @Path("page") page: Int
    ): Response<MovieDto>

    @GET(RelativeConstant.popular + "{page}")
    suspend fun getPopularMovies(
        @Path("page") page: Int
    ): Response<MovieDto>

    @GET(RelativeConstant.nowPlaying + "{page}")
    suspend fun getNowPlayingMovies(
        @Path("page") page: Int
    ): Response<MovieDto>

    @GET("{movieId}")
    suspend fun getMovieDetailById(
        @Query("movieId") movieId: String
    ): Response<MovieDetailDto>

}