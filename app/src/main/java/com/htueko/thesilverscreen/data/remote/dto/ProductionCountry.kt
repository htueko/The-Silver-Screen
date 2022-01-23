package com.htueko.thesilverscreen.data.remote.dto


import com.squareup.moshi.Json

/**
 * data transfer object for movie detail
 * @see [MovieDetailDto]
 */
data class ProductionCountry(
    @Json(name = "iso_3166_1")
    val iso31661: String = "",
    @Json(name = "name")
    val name: String = ""
)