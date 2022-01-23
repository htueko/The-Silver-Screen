package com.htueko.thesilverscreen.data.remote.dto


import com.squareup.moshi.Json

/**
 *  data transfer object for list of movie response
 *  @see [Movie]
 *  @see [Dates]
 */
data class MovieDto(
    @Json(name = "dates")
    val dates: Dates = Dates(),
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "results")
    val movies: List<Movie> = listOf(),
    @Json(name = "total_pages")
    val totalPages: Int = 0,
    @Json(name = "total_results")
    val totalResults: Int = 0
)