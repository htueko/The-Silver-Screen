package com.htueko.thesilverscreen.di

import com.htueko.thesilverscreen.data.remote.mapper.RemoteMapper
import com.htueko.thesilverscreen.data.remote.service.ApiHelper
import com.htueko.thesilverscreen.data.repository.MovieRepositoryImpl
import com.htueko.thesilverscreen.domain.repository.MovieRepository
import com.htueko.thesilverscreen.domain.usecase.GetMovieDetailByIdUseCase
import com.htueko.thesilverscreen.domain.usecase.GetNowPlayingMoviesUseCase
import com.htueko.thesilverscreen.domain.usecase.GetPopularMoviesUseCase
import com.htueko.thesilverscreen.domain.usecase.GetTopRatedMoviesUseCase
import com.htueko.thesilverscreen.domain.usecase.GetUpcomingMoviesUseCase
import com.htueko.thesilverscreen.domain.usecase.MovieUseCases
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * to bind the movie repository interface to repository implementation class
     */
    @Binds
    abstract fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

    companion object {

        /**
         * to provide wrapper class for mapping from data layer to domain layer
         * @see [RemoteMapper]
         */
        @Provides
        @Singleton
        fun provideRemoteMapper(): RemoteMapper {
            return RemoteMapper()
        }

        /**
         * to provide repository implementation class with api interface and wrapper class
         * @param [service] - api service interface
         * @param [remoteMapper] - wrapper mapper class
         */
        @Provides
        @Singleton
        fun provideMovieRepository(
            service: ApiHelper,
            remoteMapper: RemoteMapper
        ): MovieRepositoryImpl {
            return MovieRepositoryImpl(service, remoteMapper)
        }

        /**
         * to provide wrapper class of usecase(s)
         * @param [repository] - repository interface
         * @return [MovieUseCases] - wrapper class of usecase(s)
         *
         */
        @Provides
        @Singleton
        fun provideMovieUseCases(repository: MovieRepository): MovieUseCases {
            return MovieUseCases(
                getMovieDetailById = GetMovieDetailByIdUseCase(repository),
                getNowPlayingMovies = GetNowPlayingMoviesUseCase(repository),
                getPopularMovies = GetPopularMoviesUseCase(repository),
                getTopRatedMovies = GetTopRatedMoviesUseCase(repository),
                getUpComingMovies = GetUpcomingMoviesUseCase(repository),
            )
        }
    }
}
