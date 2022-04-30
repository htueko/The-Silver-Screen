package com.htueko.thesilverscreen.data.remote.dto

import com.squareup.moshi.Json

/**
 * data transfer object for movie detail
 * @see [MovieDetailDto]
 */
data class ProductionCompany(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "logo_path")
    val logoPath: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "origin_country")
    val originCountry: String = ""
)
