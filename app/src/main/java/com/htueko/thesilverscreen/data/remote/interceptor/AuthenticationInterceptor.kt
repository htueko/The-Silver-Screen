package com.htueko.thesilverscreen.data.remote.interceptor

import com.htueko.thesilverscreen.data.remote.RemoteConstant
import com.htueko.thesilverscreen.data.remote.RequireConstant
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor used to intercept the actual request and
 * to supply Tmdp API Key in REST API calls via a custom header.
 */
class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(RequireConstant.apiKey, RemoteConstant.tmdbApiKey)
            .build()
        return chain.proceed(newRequest)
    }
}
