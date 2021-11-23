package com.htueko.thesilverscreen.data.remote.service

import com.htueko.thesilverscreen.data.remote.RemoteConstant
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import timber.log.Timber

private const val TIME_OUT = 50_000

val httpClient = HttpClient(Android) {

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        url {
            protocol = URLProtocol.HTTPS
            host = RemoteConstant.tmdbBaseUrl
        }
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
        acceptContentTypes = acceptContentTypes + ContentType.Application.Json
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Timber.v("Logger Ktor => $message")
            }
        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Timber.d("HTTP status: ${response.status.value}")
        }
    }
}
