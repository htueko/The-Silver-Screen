package com.htueko.thesilverscreen.domain.usecase

import com.htueko.thesilverscreen.domain.model.MovieDetail
import com.htueko.thesilverscreen.domain.model.status.ResultOf
import com.htueko.thesilverscreen.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailByIdUseCase @Inject constructor(
    private val repo: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): ResultOf<MovieDetail> {
        val response = repo.getMovieDetailById(movieId)
        return response
    }
}
