package com.jimenez.course.data.network.services

import com.jimenez.course.data.network.models.MoviesPopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesService {

    @GET(value = "movie/popular")
    suspend fun getPopularMovies(
        @Query(value = "api_key")
        apiKey: String,
        @Query(value = "page")
        page: Int,
        @Query(value = "language")
        language: String
    ): Response<MoviesPopularResponse>

}