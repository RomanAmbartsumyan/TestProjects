package com.example.testapp.objects

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Register(val phone: String, val password: String)