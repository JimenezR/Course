package com.jimenez.course.data.network.models

import com.google.gson.annotations.SerializedName

data class MoviesPopularResponse(
    val page: Int? = null,
    val results: List<Result>? = null,
    @SerializedName(value = "total_pages")
    val totalPages: Int? = null,
    @SerializedName(value = "total_results")
    val totalResults: Int? = null
)