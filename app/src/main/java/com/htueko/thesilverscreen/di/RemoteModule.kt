package com.htueko.thesilverscreen.di

import com.htueko.thesilverscreen.data.remote.RemoteConstant
import com.htueko.thesilverscreen.data.remote.interceptor.AuthenticationInterceptor
import com.htueko.thesilverscreen.data.remote.interceptor.NetworkStatusInterceptor
import com.htueko.thesilverscreen.data.remote.service.ApiHelper
import com.htueko.thesilverscreen.data.remote.service.RemoteApiImpl
import com.htueko.thesilverscreen.data.remote.service.TmdbApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
         * to get the base url of the remote service
         * @see [RemoteConstant.tmdbBaseUrl]
         */
        @Provides
        fun provideBaseUrl() = RemoteConstant.tmdbBaseUrl

        /**
         * to provide okhttp client with interceptors
         * @param [httpLoggingInterceptor] log interceptor
         * @param [networkStatusInterceptor] to provide network connection status
         * @param [authenticationInterceptor] to provide api key for every call via header
         */
        @Provides
        fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            networkStatusInterceptor: NetworkStatusInterceptor,
            authenticationInterceptor: AuthenticationInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(networkStatusInterceptor)
                .addInterceptor(authenticationInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }

        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(provideBaseUrl())
            .client(okHttpClient)
            .build()

        @Provides
        @Singleton
        fun provideApiHelper(apiHelper: RemoteApiImpl): ApiHelper = apiHelper

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): TmdbApiService =
            retrofit.create(TmdbApiService::class.java)

    }
}
