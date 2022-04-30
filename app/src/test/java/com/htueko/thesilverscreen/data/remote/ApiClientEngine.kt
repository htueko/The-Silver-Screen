package com.htueko.thesilverscreen.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.HttpResponseValidator
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.serialization.json.Json

@Suppress("ThrowsCount")
object ApiClientEngine {

    fun getMockEngine(content: String, httpStatusCode: HttpStatusCode): MockEngine {
        return MockEngine { request ->
            respond(
                content = content,
                status = httpStatusCode,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
    }

    fun getHttpClient(engine: MockEngine): HttpClient {
        val httpClient: HttpClient = HttpClient(engine) {

            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
                accept(ContentType("application", "vnd.api+json"))
            }

            install(ResponseObserver) {
                onResponse { response ->
                    println("HTTP status: ${response.status.value}")
                }
            }

            HttpResponseValidator {
                validateResponse { response: HttpResponse ->
                    val statusCode = response.status.value
                    println("HTTP status: $statusCode")

                    when (statusCode) {
                        in 300..399 -> throw RedirectResponseException(response)
                        in 400..499 -> throw ClientRequestException(response)
                        in 500..599 -> throw ServerResponseException(response)
                    }

                    if (statusCode >= 600) {
                        throw ResponseException(response)
                    }

                    handleResponseException { cause: Throwable ->
                        throw cause
                    }
                }
            }
        }
        return httpClient
    }
}
