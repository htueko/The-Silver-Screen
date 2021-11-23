package com.htueko.thesilverscreen.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * data transfer object for movie detail
 * @see [MovieDetailDto]
 */
@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1")
    val iso31661: String? = null,
    @SerialName("name")
    val name: String? = null
)
