package com.example.testapp.fragments.register

import com.example.testapp.connection.Api
import com.example.testapp.objects.Register

class RegisterFragmentRepositoryImpl(private val api: Api) : RegisterFragmentRepository {

    override suspend fun getPhone(): String = api.getPhone().phoneMask

    override suspend fun auth(register: Register): Boolean {
        return when (api.auth(register).code()) {
            200 -> true
            else -> false
        }

    }
}