package com.example.testapp.fragments.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.testapp.objects.Register

class RegisterFragmentViewModel(
    application: Application,
    private val repository: RegisterFragmentRepository
) : AndroidViewModel(application) {


    suspend fun getPhone(): String = repository.getPhone()

    suspend fun auth(register: Register): Boolean = repository.auth(register)
}