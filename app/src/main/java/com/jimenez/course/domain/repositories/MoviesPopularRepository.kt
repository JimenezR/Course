package com.jimenez.course.domain.repositories

import com.jimenez.course.data.network.models.MoviesPopularResponse
import retrofit2.Response

interface MoviesPopularRepository {

    suspend fun getMoviesPopularRepository(
        apiKey: String,
        page: Int,
        language: String
    ): Response<MoviesPopularResponse>

}