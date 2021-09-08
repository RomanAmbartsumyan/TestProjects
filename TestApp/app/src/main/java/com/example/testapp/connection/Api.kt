package com.example.testapp.connection

import com.example.testapp.objects.Card
import com.example.testapp.objects.Phone
import com.example.testapp.objects.Register
import com.example.testapp.objects.ResponseFromServer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("phone_masks")
    suspend fun getPhone(): Phone

    @POST("auth")
    suspend fun auth(@Body register: Register): Response<ResponseFromServer>

    @GET("posts")
    suspend fun getPosts(): List<Card>
}