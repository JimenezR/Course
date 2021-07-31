package com.jimenez.course.data.local.entites

import androidx.room.*
import com.jimenez.course.data.local.converter.StringConverter

@Entity(tableName = "movie")
data class Movie(
    val adult: Boolean? = null,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,
    @TypeConverters(StringConverter::class)
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>? = null,
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String? = null,
    @ColumnInfo(name = "original_title")
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int? = null
)