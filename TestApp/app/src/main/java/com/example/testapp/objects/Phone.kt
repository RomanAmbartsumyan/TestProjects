package com.example.testapp.objects

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Phone(val phoneMask: String)