package com.example.flow.db.movies

import androidx.room.TypeConverter
import com.example.flow.MovieType

class MovieConverter {
    @TypeConverter
    fun convertMovieTypeToString(movieType: MovieType): String = movieType.name
}