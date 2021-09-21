package com.example.workmanager.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainFragmentRepository(application)

    fun loadFile(url: String) {
        repository.loadFile(url)
    }

    fun stopDownload() {
        repository.stopDownload()
    }
}