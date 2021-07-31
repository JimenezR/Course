package com.jimenez.course.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jimenez.course.data.local.entites.Movie

@Dao
interface MovieDao {

    @Query("SELECT * from movie")
    suspend fun getMovies(): List<Movie>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>): List<Long>

    @Query("DELETE FROM movie")
    suspend fun deleteMovies()

}