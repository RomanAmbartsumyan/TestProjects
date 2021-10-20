package com.example.flow.connection

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Network {

    private const val API_KEY = "5e3e1128"

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(
            AddApiKeyInterceptor(
                API_KEY
            )
        )
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://www.omdbapi.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    val OmdbApi: OmdbApi
        get() = retrofit.create()
}