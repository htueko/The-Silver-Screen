package com.htueko.thesilverscreen.data.remote.mapper


import com.htueko.thesilverscreen.data.remote.dto.MovieDetailDto
import com.htueko.thesilverscreen.data.remote.dto.MovieDto
import com.htueko.thesilverscreen.domain.model.BelongsToCollection
import com.htueko.thesilverscreen.domain.model.Genre
import com.htueko.thesilverscreen.domain.model.Movie
import com.htueko.thesilverscreen.domain.model.MovieDetail
import com.htueko.thesilverscreen.domain.model.ProductionCompany
import com.htueko.thesilverscreen.domain.model.ProductionCountry
import com.htueko.thesilverscreen.domain.model.SpokenLanguage
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import javax.inject.Singleton
import com.htueko.thesilverscreen.data.remote.dto.BelongsToCollection as BelongToCollectionResponse
import com.htueko.thesilverscreen.data.remote.dto.Genre as GenreResponse
import com.htueko.thesilverscreen.data.remote.dto.Movie as MovieResponse
import com.htueko.thesilverscreen.data.remote.dto.ProductionCompany as ProductionCompanyResponse
import com.htueko.thesilverscreen.data.remote.dto.ProductionCountry as ProductionCountryResponse
import com.htueko.thesilverscreen.data.remote.dto.SpokenLanguage as SpokenLanguageResponse

/**
 * to convert from data transfer object of data layer to model object of domain layer
 */
@Singleton
class RemoteMapper {

    /**
     * to map the response dto to list of movie model
     * @param response - generic [ResultOf] of [MovieDto]
     * @return [ResultOf] of [List] of [Movie]
     */
    fun mapMovie(response: ResultOf<MovieDto>): ResultOf<List<Movie>> {
        return when (response) {
            is ResultOf.ApiError -> ResultOf.ApiError(response.message)
            is ResultOf.NetworkError -> ResultOf.NetworkError(response.throwable)
            is ResultOf.Success -> ResultOf.Success(
                mapFromResponseToModelList(response.data.results ?: emptyList())
            )
        }
    }

    /**
     * to map the response dto to movie detail model
     * @param response - generic [ResultOf] of [MovieDetailDto]
     * @return [ResultOf] of [MovieDetail]
     */
    fun mapMovieDetail(response: ResultOf<MovieDetailDto>): ResultOf<MovieDetail> {
        return when (response) {
            is ResultOf.ApiError -> ResultOf.ApiError(response.message)
            is ResultOf.NetworkError -> ResultOf.NetworkError(response.throwable)
            is ResultOf.Success -> ResultOf.Success(
                mapFromResponseToModel(response.data)
            )
        }
    }

    private fun mapFromResponseToModelList(data: List<MovieResponse>): List<Movie> {
        return data.map {
            Movie(
                it.adult ?: false,
                it.backdropPath ?: "",
                it.genreIds ?: emptyList(),
                it.id ?: 0,
                it.originalLanguage ?: "",
                it.originalTitle ?: "",
                it.overview ?: "",
                it.popularity ?: 0.0,
                it.posterPath ?: "",
                it.releaseDate ?: "",
                it.title ?: "",
                it.video ?: false,
                it.voteAverage ?: 0.0,
                it.voteCount ?: 0
            )
        }
    }

    private fun mapBelongsToCollection(data: BelongToCollectionResponse?): BelongsToCollection {
        return BelongsToCollection(
            backdropPath = data?.backdropPath ?: "",
            id = data?.id ?: 0,
            name = data?.name ?: "",
            posterPath = data?.posterPath ?: ""
        )
    }

    private fun mapGenres(data: List<GenreResponse>): List<Genre> {
        return data.map {
            Genre(
                id = it.id ?: 0,
                name = it.name ?: ""
            )
        }
    }

    private fun mapCompanies(data: List<ProductionCompanyResponse>): List<ProductionCompany> {
        return data.map {
            ProductionCompany(
                id = it.id ?: 0,
                logoPath = it.logoPath ?: "",
                originCountry = it.originCountry ?: "",
                name = it.name ?: ""
            )
        }
    }

    private fun mapCountries(data: List<ProductionCountryResponse>): List<ProductionCountry> {
        return data.map {
            ProductionCountry(
                iso31661 = it.iso31661 ?: "",
                name = it.name ?: ""
            )
        }
    }

    private fun mapLanguages(data: List<SpokenLanguageResponse>): List<SpokenLanguage> {
        return data.map {
            SpokenLanguage(
                englishName = it.englishName ?: "",
                iso6391 = it.iso6391 ?: "",
                name = it.name ?: ""
            )
        }
    }

    private fun mapFromResponseToModel(data: MovieDetailDto): MovieDetail {
        return MovieDetail(
            adult = data.adult ?: false,
            backdropPath = data.backdropPath ?: "",
            belongsToCollection = mapBelongsToCollection(data.belongsToCollection),
            budget = data.budget ?: 0,
            genres = mapGenres(data.genres ?: emptyList()),
            homepage = data.homepage ?: "",
            id = data.id ?: 0,
            imdbId = data.imdbId ?: "",
            originalLanguage = data.originalLanguage ?: "",
            originalTitle = data.originalTitle ?: "",
            overview = data.overview ?: "",
            popularity = data.popularity ?: 0.0,
            posterPath = data.posterPath ?: "",
            productionCompanies = mapCompanies(data.productionCompanies ?: emptyList()),
            productionCountries = mapCountries(data.productionCountries ?: emptyList()),
            releaseDate = data.releaseDate ?: "",
            revenue = data.revenue ?: 0,
            runtime = data.runtime ?: 0,
            spokenLanguages = mapLanguages(data.spokenLanguages ?: emptyList()),
            status = data.status ?: "",
            tagline = data.tagline ?: "",
            title = data.title ?: "",
            video = data.video ?: false,
            voteAverage = data.voteAverage ?: 0.0,
            voteCount = data.voteCount ?: 0
        )
    }
}
