package com.example.flow.connection

import com.example.flow.db.movies.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OmdbResponse(
    @Json(name = "Search")
    val movies: List<Movie>
)