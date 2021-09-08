package com.example.testapp.objects

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseFromServer(val success: Boolean)