package com.htueko.thesilverscreen.di

import com.htueko.thesilverscreen.data.remote.service.ApiHelper
import com.htueko.thesilverscreen.data.remote.service.TmdbApiService
import com.htueko.thesilverscreen.data.remote.service.httpClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    /**
     * to bind the ApiHelper interface to TmdbApiService class
     */
    @Binds
    abstract fun bindApiService(service: TmdbApiService): ApiHelper

    companion object {
        /**
         * to provide the ktor android http client
         * @see [httpClient]
         */
        @Provides
        @Singleton
        fun provideHttpClient(): HttpClient {
            return httpClient
        }

        /**
         * to provide Api service implementation class with ktor android client
         * @param httpClient - ktor android http client
         * @see [httpClient]
         * @see [TmdbApiService]
         */
        @Provides
        @Singleton
        fun provideApiService(httpClient: HttpClient): TmdbApiService {
            return TmdbApiService(httpClient)
        }
    }
}
