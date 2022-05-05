package com.htueko.thesilverscreen.data.remote.service

import android.os.Build
import com.google.common.truth.Truth
import com.htueko.thesilverscreen.data.remote.HttpStatusCodeCollection
import com.htueko.thesilverscreen.data.remote.dto.MovieDetailResponse
import com.htueko.thesilverscreen.data.remote.dto.NowPlayingResponse
import com.htueko.thesilverscreen.data.remote.dto.PopularResponse
import com.htueko.thesilverscreen.data.remote.dto.TopRatedResponse
import com.htueko.thesilverscreen.data.remote.dto.UpComingResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [Build.VERSION_CODES.P])
class TestApiService {

    private val nowPlayingContent = NowPlayingResponse.getSuccessResponse()
    private val popularContent = PopularResponse.getSuccessResponse()
    private val upcomingContent = UpComingResponse.getSuccessResponse()
    private val topRatedContent = TopRatedResponse.getSuccessResponse()
    private val movieDetailContent = MovieDetailResponse.getSuccessResponse()

    @Test
    fun `get now playing movies return success`() = runBlocking {
        /**
         * Given
         */
        // to get the MockEngine
        val mockEngine =
            ApiClientEngine.getMockEngine(nowPlayingContent, HttpStatusCodeCollection.SUCCESS_200)
        // to get the MockHttpClient
        val mockHttpClient = ApiClientEngine.getHttpClient(mockEngine)
        // to get the ApiClient
        val apiClient = ApiClient(mockHttpClient)

        /**
         * When
         */
        // to get the response dto
        val response = apiClient.getNowPlayingMovies(1)
        // to get first item from the list
        val firstItem = response.results?.get(0)
        /**
         * Then
         */
        // to test the page is equal to 1
        Truth.assertThat(response.page).isEqualTo(1)
        // to test the list of movies is equal to 20
        Truth.assertThat(response.results?.size).isEqualTo(20)
        // to test the entire page of the response is 126
        Truth.assertThat(response.totalPages).isEqualTo(126)
        // to test the first movie id
        Truth.assertThat(firstItem?.id).isEqualTo(566525)
        // to test the first movie title
        Truth.assertThat(firstItem?.title).isEqualTo("Shang-Chi and the Legend of the Ten Rings")
    }

    @Test
    fun `get popular movies return success`() = runBlocking {
        /**
         * Given
         */
        // to get the MockEngine
        val mockEngine =
            ApiClientEngine.getMockEngine(popularContent, HttpStatusCodeCollection.SUCCESS_200)
        // to get the MockHttpClient
        val mockHttpClient = ApiClientEngine.getHttpClient(mockEngine)
        // to get the ApiClient
        val apiClient = ApiClient(mockHttpClient)

        /**
         * When
         */
        // to get the response dto
        val response = apiClient.getPopularMovies(1)
        // to get second item from the list
        val secondItem = response.results?.get(1)
        /**
         * Then
         */
        // to test the page is equal to 1
        Truth.assertThat(response.page).isEqualTo(1)
        // to test the list of movies is equal to 20
        Truth.assertThat(response.results?.size).isEqualTo(20)
        // to test the entire page of the response is 126
        Truth.assertThat(response.totalPages).isEqualTo(500)
        // to test the first movie id
        Truth.assertThat(secondItem?.id).isEqualTo(370172)
        // to test the first movie title
        Truth.assertThat(secondItem?.title).isEqualTo("No Time to Die")
    }

    @Test
    fun `get top rated movies return success`() = runBlocking {
        /**
         * Given
         */
        // to get the MockEngine
        val mockEngine =
            ApiClientEngine.getMockEngine(topRatedContent, HttpStatusCodeCollection.SUCCESS_200)
        // to get the MockHttpClient
        val mockHttpClient = ApiClientEngine.getHttpClient(mockEngine)
        // to get the ApiClient
        val apiClient = ApiClient(mockHttpClient)

        /**
         * When
         */
        // to get the response dto
        val response = apiClient.getTopRatedMovies(1)
        // to get first item from the list
        val firstItem = response.results?.get(0)
        /**
         * Then
         */
        // to test the page is equal to 1
        Truth.assertThat(response.page).isEqualTo(1)
        // to test the list of movies is equal to 20
        Truth.assertThat(response.results?.size).isEqualTo(20)
        // to test the entire page of the response is 126
        Truth.assertThat(response.totalPages).isEqualTo(468)
        // to test the first movie id
        Truth.assertThat(firstItem?.id).isEqualTo(19404)
        // to test the first movie title
        Truth.assertThat(firstItem?.title).isEqualTo("Dilwale Dulhania Le Jayenge")
    }

    @Test
    fun `get upcoming movies return success`() = runBlocking {
        /**
         * Given
         */
        // to get the MockEngine
        val mockEngine =
            ApiClientEngine.getMockEngine(upcomingContent, HttpStatusCodeCollection.SUCCESS_200)
        // to get the MockHttpClient
        val mockHttpClient = ApiClientEngine.getHttpClient(mockEngine)
        // to get the ApiClient
        val apiClient = ApiClient(mockHttpClient)

        /**
         * When
         */
        // to get the response dto
        val response = apiClient.getUpComingMovies(1)
        // to get second item from the list
        val firstItem = response.results?.get(1)
        /**
         * Then
         */
        // to test the page is equal to 1
        Truth.assertThat(response.page).isEqualTo(1)
        // to test the list of movies is equal to 20
        Truth.assertThat(response.results?.size).isEqualTo(20)
        // to test the entire page of the response is 126
        Truth.assertThat(response.totalPages).isEqualTo(20)
        // to test the first movie id
        Truth.assertThat(firstItem?.id).isEqualTo(580489)
        // to test the first movie title
        Truth.assertThat(firstItem?.title).isEqualTo("Venom: Let There Be Carnage")
    }

    @Test
    fun `get movie detail return success`() = runBlocking {
        /**
         * Given
         */
        // to get the MockEngine
        val mockEngine =
            ApiClientEngine.getMockEngine(movieDetailContent, HttpStatusCodeCollection.SUCCESS_200)
        // to get the MockHttpClient
        val mockHttpClient = ApiClientEngine.getHttpClient(mockEngine)
        // to get the ApiClient
        val apiClient = ApiClient(mockHttpClient)

        /**
         * When
         */
        // to get the response dto
        val response = apiClient.getMovieDetailById(1)
        /**
         * Then
         */
        // to test the adult field
        Truth.assertThat(response.adult).isFalse()
        // to test the first movie id
        Truth.assertThat(response.id).isEqualTo(585245)
        // to test the imdb id
        Truth.assertThat(response.imdbId).isEqualTo("tt2397461")
        // to test the movie title
        Truth.assertThat(response.title).isEqualTo("Clifford the Big Red Dog")
    }
}
