package com.example.flow.connection

import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    //http://www.omdbapi.com/?t=joker&apikey=5e3e1128

    @GET("/")
    suspend fun search(
        @Query("type") movieType: String,
        @Query("s") searchText: String
    ): OmdbResponse
}