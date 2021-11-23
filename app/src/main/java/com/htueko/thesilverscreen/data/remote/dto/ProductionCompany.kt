package com.htueko.thesilverscreen.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * data transfer object for movie detail
 * @see [MovieDetailDto]
 */
@Serializable
data class ProductionCompany(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("logo_path")
    val logoPath: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("origin_country")
    val originCountry: String? = null
)
