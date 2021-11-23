package com.htueko.thesilverscreen.data.remote

import com.htueko.thesilverscreen.BuildConfig

/**
 * constant for remote api
 */
object RemoteConstant {
    // tmdb api key
    const val tmdbApiKey = BuildConfig.TMDB_API_KEY

    // base url
    const val tmdbBaseUrl = "api.themoviedb.org/3"
}

/**
 * constant for relative params
 */
object RelativeConstant {
    /**
     * Start of Get
     */
    // upcoming movies
    const val upcoming = "movie/upcoming"

    // top rated movies
    const val topRated = "movie/top_rated"

    // popular movies
    const val popular = "movie/popular"

    // now playing movies
    const val nowPlaying = "movie/now_playing"

    // videos
    const val videos = "movie/{movie_id}/videos"

    // translations
    const val translations = "movie/{movie_id}/translations"

    // similar movies
    const val similar = "movie/{movie_id}/similar"

    // reviews
    const val reviews = "movie/{movie_id}/reviews"

    // release dates
    const val releaseDate = "movie/{movie_id}/release_dates"

    // recommendations
    const val recommendations = "movie/{movie_id}/recommendations"

    // keywords
    const val keywords = "movie/{movie_id}/keywords"

    // images
    const val images = "movie/{movie_id}/images"

    // credits
    const val credits = "movie/{movie_id}/credits"

    // alternative titles
    const val alternativeTitles = "movie/{movie_id}/alternative_titles"

    // movie detail
    const val detail = "movie/"

    /**
     * End of Get, Start of Search
     */

    // search movies
    const val searchMovies = "search/movie"

    // search peoples
    const val searchPeople = "search/person"

    // search multiple
    const val searchMulti = "search/multi"

    // search TV shows
    const val searchTvShows = "search/tv"

    /**
     * End of Search, Start of Configuration
     */

    // configuration
    const val configuration = "configuration"

    // genre list of movie
    const val movieGenre = "genre/movie/list"

    // genre list of tv show
    const val tvGenre = "genre/tv/list"

    // all available languages
    const val configLanguage = "configuration/languages"

    // all available countries
    const val configCountries = "configuration/countries"
}

/**
 * constant key for relative params
 */
object RequireConstant {
    // api constant
    const val apiKey = "api_key"

    // movie id
    const val movieId = "movie_id"

    // query
    const val query = "query"
}

/**
 * constant for optional params
 */
object OptionalConstant {
    // language param
    const val language = "language"

    // default language param
    const val defaultLanguage = "en-US"

    // page param
    const val page = "page"

    // default page param
    const val defaultPage = "1"

    // region param
    const val region = "region"

    // default region param
    const val defaultRegion = "US"

    // language written on images
    const val includeImageLanguage = "include_image_language"

    // default language written on images
    const val defaultIncludeImageLanguage = "en"

    // country
    const val country = "country"

    // default country
    const val defaultCountry = "US"

    // adult
    const val adult = "include_adult"

    // default adult
    const val defaultAdult = false
}
