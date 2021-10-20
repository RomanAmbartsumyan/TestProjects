package com.example.flow.db.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = MoviesContract.TABLE_NAME)
@TypeConverters(MovieConverter::class)
data class Movie(
    @Json(name = "imdbID")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = MoviesContract.Columns.ID)
    val id: String,
    @Json(name = "Title")
    @ColumnInfo(name = MoviesContract.Columns.TITLE)
    val title: String,
    @Json(name = "Poster")
    @ColumnInfo(name = MoviesContract.Columns.POSTER)
    val poster: String
)