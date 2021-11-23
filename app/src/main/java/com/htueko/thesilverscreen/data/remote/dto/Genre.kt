package com.htueko.thesilverscreen.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * data transfer object for movie detail
 * @see [MovieDetailDto]
 */
@Serializable
data class Genre(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null
)
