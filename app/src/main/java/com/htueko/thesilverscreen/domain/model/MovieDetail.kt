package com.htueko.thesilverscreen.domain.model

/**
 * simple data object to hold the properties for movie detail
 */
data class MovieDetail(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val belongsToCollection: BelongsToCollection? = null,
    val budget: Int = 0,
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val imdbId: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val productionCompanies: List<ProductionCompany> = listOf(),
    val productionCountries: List<ProductionCountry> = listOf(),
    val releaseDate: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
) {
    companion object {
        fun getBackDropImageUrl(imageUrl: String?): String {
            val path = "https://image.tmdb.org/t/p/w500"
            return "$path$imageUrl"
        }
    }
}

data class BelongsToCollection(
    val backdropPath: String = "",
    val id: Int = 0,
    val name: String = "",
    val posterPath: String = ""
) {
    companion object {
        fun getBackDropImageUrl(imageUrl: String?): String {
            val path = "https://image.tmdb.org/t/p/w500"
            return "$path$imageUrl"
        }
    }
}

data class Genre(
    val id: Int = 0,
    val name: String = ""
)

data class ProductionCompany(
    val id: Int = 0,
    val logoPath: String = "",
    val name: String = "",
    val originCountry: String = ""
) {
    companion object {
        fun getLogoImageUrl(imageUrl: String?): String {
            val path = "https://image.tmdb.org/t/p/w500"
            return "$path$imageUrl"
        }
    }
}

data class ProductionCountry(
    val iso31661: String = "",
    val name: String = ""
)

data class SpokenLanguage(
    val englishName: String = "",
    val iso6391: String = "",
    val name: String = ""
)
