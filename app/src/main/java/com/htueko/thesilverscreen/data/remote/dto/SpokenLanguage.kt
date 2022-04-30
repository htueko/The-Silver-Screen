package com.htueko.thesilverscreen.data.remote.dto

import com.squareup.moshi.Json

/**
 * data transfer object for movie detail
 * @see [MovieDetailDto]
 */
data class SpokenLanguage(
    @Json(name = "english_name")
    val englishName: String = "",
    @Json(name = "iso_639_1")
    val iso6391: String = "",
    @Json(name = "name")
    val name: String = ""
)
