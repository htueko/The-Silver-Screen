package com.htueko.thesilverscreen.domain.model.status

/**
 * to get the generic response from server
 * @see [Success] - for success response from server
 * @see [ApiError] - for failure response from server
 * @see [NetworkError] - for network error
 */
sealed class ResultOf<T> {

    class Success<T>(val data: T) : ResultOf<T>()

    class ApiError<T>(val message: String) : ResultOf<T>()

    class NetworkError<T>(val throwable: Throwable) : ResultOf<T>()
}
