package com.htueko.thesilverscreen.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * data transfer object for movie detail
 * @see [MovieDetailDto]
 */
@Serializable
data class SpokenLanguage(
    @SerialName("english_name")
    val englishName: String? = null,
    @SerialName("iso_639_1")
    val iso6391: String? = null,
    @SerialName("name")
    val name: String? = null
)
