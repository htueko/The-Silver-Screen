package com.htueko.thesilverscreen.data.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class TestApiDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            TestApiEndPoint.UPCOMING_MOVIES.value -> MockResponse()
                .setResponseCode(StatusCode.SUCCESS.code)
            TestApiEndPoint.TOP_RATED_MOVIES.value -> MockResponse()
                .setResponseCode(StatusCode.SUCCESS.code)
            TestApiEndPoint.POPULAR_MOVIES.value -> MockResponse()
                .setResponseCode(StatusCode.SUCCESS.code)
            TestApiEndPoint.NOW_PLAYING_MOVIES.value -> MockResponse()
                .setResponseCode(StatusCode.SUCCESS.code)
            TestApiEndPoint.DETAIL_MOVIES.value -> MockResponse()
                .setResponseCode(StatusCode.SUCCESS.code)
            else -> MockResponse()
                .setResponseCode(StatusCode.NOT_FOUND.code)
        }
    }
}
