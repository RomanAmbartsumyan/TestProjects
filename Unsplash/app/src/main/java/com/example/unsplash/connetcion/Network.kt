package com.example.unsplash.connetcion

import com.example.unsplash.connetcion.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object Network {
    var accessToken = ""

    private var okHttpClient = getOkHttpClient()

    private var retrofit = getRetrofit()


    fun rebuildOkHtpClientAndRetrofit() {
        okHttpClient = getOkHttpClient()
        retrofit = getRetrofit()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(AuthInterceptor(accessToken))
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}