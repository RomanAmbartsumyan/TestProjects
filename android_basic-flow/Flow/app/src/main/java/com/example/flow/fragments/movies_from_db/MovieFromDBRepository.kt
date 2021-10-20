package com.example.flow.fragments.movies_from_db

import com.example.flow.db.Database
import com.example.flow.db.movies.Movie

class MovieFromDBRepository {
    private val movieDao = Database.instance.movieDao()

    suspend fun getMovies(): List<Movie> {
        return movieDao.getAllMovies()
    }
}