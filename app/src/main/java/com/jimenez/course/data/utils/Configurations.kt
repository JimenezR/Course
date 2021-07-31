package com.jimenez.course.data.utils

class Configurations {

    companion object {
        const val MOVIE_API_KEY = "1f54bd990f1cdfb230adb312546d765d"
    }

    private val currentConfiguration = "dev"
    private val configuration = mapOf(
        "dev" to Configuration(
            baseURL = "https://api.themoviedb.org/3/"
        ),
        "qa" to Configuration(
            baseURL = "https://api.qa.themoviedb.org/3/"
        ),
        "release" to Configuration(
            baseURL = "https://api.themoviedb.org/3/"
        )
    )

    private var baseURL: String = ""

    init {
        val selectConf = configuration[currentConfiguration]
        selectConf?.baseURL?.let { baseURL = it }
    }

    fun getBaseUrl(): String = baseURL

}

private data class Configuration(
    val baseURL: String = ""
)