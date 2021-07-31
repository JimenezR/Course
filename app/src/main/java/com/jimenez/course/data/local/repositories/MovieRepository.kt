package com.jimenez.course.data.local.repositories

import com.jimenez.course.data.local.dao.MovieDao
import com.jimenez.course.data.local.entites.Movie

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun getMovies(): List<Movie>? = movieDao.getMovies()

    suspend fun insertMovies(movies: List<Movie>): List<Long> = movieDao.insertMovies(movies)

}