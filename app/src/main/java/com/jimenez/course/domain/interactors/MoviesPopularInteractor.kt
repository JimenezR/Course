package com.jimenez.course.domain.interactors

import com.jimenez.course.data.network.models.MoviesPopularResponse
import com.jimenez.course.domain.repositories.MoviesPopularRepository
import retrofit2.Response

class MoviesPopularInteractor(private var moviesPopularRepository: MoviesPopularRepository) {

    suspend fun getMoviesPopular(
        apiKey: String,
        page: Int,
        language: String
    ): Response<MoviesPopularResponse> {
        return moviesPopularRepository.getMoviesPopularRepository(
            apiKey = apiKey,
            page = page,
            language = language
        )
    }

}