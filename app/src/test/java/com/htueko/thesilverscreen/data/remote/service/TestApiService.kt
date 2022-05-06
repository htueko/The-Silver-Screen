package com.htueko.thesilverscreen.data.remote.service

import android.os.Build
import com.google.common.truth.Truth.assertThat
import com.htueko.thesilverscreen.data.util.StatusCode
import com.htueko.thesilverscreen.data.util.TestApiDispatcher
import com.htueko.thesilverscreen.data.util.TestApiEndPoint
import com.htueko.thesilverscreen.data.util.enqueueResponse
import com.orhanobut.logger.Logger
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [Build.VERSION_CODES.P])
class TestApiService {

    private lateinit var apiService: TmdbApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder().build()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TmdbApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    private fun setupMockWebServer(fileName: String, endPoint: TestApiEndPoint) {
        mockWebServer.apply {
            dispatcher = TestApiDispatcher()
            url(endPoint.value)
            enqueueResponse(fileName)
        }
    }

    @Test
    fun `get now playing movies return success`() = runTest {
        try {
            setupMockWebServer("NowPlayingResponse.json", TestApiEndPoint.NOW_PLAYING_MOVIES)

            val response = apiService.getNowPlayingMovies(1)

            // to test the end point
            assertThat(response.raw().request.url).isEqualTo(TestApiEndPoint.NOW_PLAYING_MOVIES.value)
            // to test the status code
            assertThat(response.code()).isEqualTo(StatusCode.SUCCESS.code)
            // to test the page is equal to 1
            assertThat(response.body()?.page).isEqualTo(1)
            // to test the list of movies is equal to 20
            assertThat(response.body()?.movies?.size).isEqualTo(20)
            // to test the entire page of the response is 126
            assertThat(response.body()?.totalPages).isEqualTo(126)
            // to test the entire result of the response is 2505
            assertThat(response.body()?.totalResults).isEqualTo(2505)
            // to test the first movie id
            assertThat(response.body()?.movies?.get(0)?.id).isEqualTo(566525)
            // to test the first movie title
            assertThat(response.body()?.movies?.get(0)?.title).isEqualTo("Shang-Chi and the Legend of the Ten Rings")
        } catch (e: Exception) {
            e.localizedMessage?.let { Logger.e(it) }
        }
    }

//    @Test
//    fun `get popular movies return success`() = runBlocking {
//
//        // to test the page is equal to 1
//        Truth.assertThat(response.page).isEqualTo(1)
//        // to test the list of movies is equal to 20
//        Truth.assertThat(response.results?.size).isEqualTo(20)
//        // to test the entire page of the response is 126
//        Truth.assertThat(response.totalPages).isEqualTo(500)
//        // to test the first movie id
//        Truth.assertThat(secondItem?.id).isEqualTo(370172)
//        // to test the first movie title
//        Truth.assertThat(secondItem?.title).isEqualTo("No Time to Die")
//    }
//
//    @Test
//    fun `get top rated movies return success`() = runBlocking {
//
//        // to test the page is equal to 1
//        Truth.assertThat(response.page).isEqualTo(1)
//        // to test the list of movies is equal to 20
//        Truth.assertThat(response.results?.size).isEqualTo(20)
//        // to test the entire page of the response is 126
//        Truth.assertThat(response.totalPages).isEqualTo(468)
//        // to test the first movie id
//        Truth.assertThat(firstItem?.id).isEqualTo(19404)
//        // to test the first movie title
//        Truth.assertThat(firstItem?.title).isEqualTo("Dilwale Dulhania Le Jayenge")
//    }
//
//    @Test
//    fun `get upcoming movies return success`() = runBlocking {
//
//        // to test the page is equal to 1
//        Truth.assertThat(response.page).isEqualTo(1)
//        // to test the list of movies is equal to 20
//        Truth.assertThat(response.results?.size).isEqualTo(20)
//        // to test the entire page of the response is 126
//        Truth.assertThat(response.totalPages).isEqualTo(20)
//        // to test the first movie id
//        Truth.assertThat(firstItem?.id).isEqualTo(580489)
//        // to test the first movie title
//        Truth.assertThat(firstItem?.title).isEqualTo("Venom: Let There Be Carnage")
//    }
//
//    @Test
//    fun `get movie detail return success`() = runBlocking {
//        // to test the adult field
//        Truth.assertThat(response.adult).isFalse()
//        // to test the first movie id
//        Truth.assertThat(response.id).isEqualTo(585245)
//        // to test the imdb id
//        Truth.assertThat(response.imdbId).isEqualTo("tt2397461")
//        // to test the movie title
//        Truth.assertThat(response.title).isEqualTo("Clifford the Big Red Dog")
//    }
}
