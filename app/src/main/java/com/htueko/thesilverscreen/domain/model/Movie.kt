package com.htueko.thesilverscreen.domain.model

/**
 * simple data object to hold the properties for movie
 */
data class Movie(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val genreIds: List<Int> = listOf(),
    val id: Int = 0,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
) {
    fun getBackDropImageUrl(imageUrl: String): String {
        val path = "https://image.tmdb.org/t/p/w500"
        return "$path$imageUrl"
    }
}
