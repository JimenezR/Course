package com.jimenez.course.data.network.repositories

import com.jimenez.course.data.network.NetworkModule
import com.jimenez.course.data.network.models.MoviesPopularResponse
import com.jimenez.course.data.network.services.PopularMoviesService
import com.jimenez.course.data.utils.Configurations
import com.jimenez.course.domain.repositories.MoviesPopularRepository
import retrofit2.Response

class MoviesPopularNetworkRepository : MoviesPopularRepository {

    private var retrofitInstance =
        NetworkModule().provideRetrofit(baseURL = Configurations().getBaseUrl())

    override suspend fun getMoviesPopularRepository(
        apiKey: String,
        page: Int,
        language: String
    ): Response<MoviesPopularResponse> {
        return NetworkModule().provideApi(
            retrofit = retrofitInstance,
            service = PopularMoviesService::class.java
        ).getPopularMovies(
            apiKey = apiKey,
            page = page,
            language = language
        )
    }

}