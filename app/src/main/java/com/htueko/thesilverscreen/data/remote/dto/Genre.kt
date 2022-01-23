package com.htueko.thesilverscreen.data.remote.dto


import com.squareup.moshi.Json

/**
 * data transfer object for movie detail
 * @see [MovieDetailDto]
 */
data class Genre(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = ""
)