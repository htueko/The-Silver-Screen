package com.htueko.thesilverscreen.data.remote.dto


import com.squareup.moshi.Json

/**
 * data transfer object for api response
 * @see [MovieDto]
 */
data class Dates(
    @Json(name = "maximum")
    val maximum: String = "",
    @Json(name = "minimum")
    val minimum: String = ""
)