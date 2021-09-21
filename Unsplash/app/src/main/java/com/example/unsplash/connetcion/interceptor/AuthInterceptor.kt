package com.example.unsplash.connetcion.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return if (token.isNotEmpty()) {
            val modifiedRequest = originalRequest.newBuilder()
                .header("Authorization", "token $token")
                .build()
            chain.proceed(modifiedRequest)
        } else {
            chain.proceed(originalRequest)
        }
    }
}