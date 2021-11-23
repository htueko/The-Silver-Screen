package com.htueko.thesilverscreen.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * data transfer object for movie detail
 * @see [MovieDetailDto]
 */
@Serializable
data class BelongsToCollection(
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null
)
