package com.htueko.thesilverscreen.data.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

fun MockWebServer.enqueueResponse(fileName: String) {
    val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
    val source = inputStream?.use { inputStream.source().buffer() }
    source?.use {
        enqueue(MockResponse().setBody(source.readString(StandardCharsets.UTF_8)))
    }
}
