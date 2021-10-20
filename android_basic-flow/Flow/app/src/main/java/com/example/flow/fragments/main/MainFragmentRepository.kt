package com.example.flow.fragments.main

import com.example.flow.MovieType
import com.example.flow.connection.Network
import com.example.flow.db.Database
import com.example.flow.db.movies.Movie
import java.util.*

class MainFragmentRepository {

    private val movieDao = Database.instance.movieDao()

    suspend fun searchMovies(movieName: String, movieType: MovieType): List<Movie> {
        return try {
            val movies = Network.OmdbApi.search(
                movieType.name.lowercase(Locale.getDefault()),
                movieName
            ).movies
            movieDao.addMovies(movies)
            return movies
        } catch (e: Exception) {
            movieDao.getAllMovies()
        }
    }
}