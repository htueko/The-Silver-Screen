package com.htueko.thesilverscreen.domain.usecase

import com.htueko.thesilverscreen.domain.model.Movie
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import com.htueko.thesilverscreen.domain.repository.MovieRepository
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repo: MovieRepository
) {
    suspend operator fun invoke(page: Int): ResultOf<List<Movie>> {
        return repo.getNowPlayingMovies(page)
    }
}
