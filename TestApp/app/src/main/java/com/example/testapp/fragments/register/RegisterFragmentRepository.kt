package com.example.testapp.fragments.register

import com.example.testapp.objects.Register

interface RegisterFragmentRepository {
    suspend fun getPhone(): String
    suspend fun auth(register: Register): Boolean
}