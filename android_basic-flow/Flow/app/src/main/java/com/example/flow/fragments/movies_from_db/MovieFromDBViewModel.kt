package com.example.flow.fragments.movies_from_db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.flow.db.movies.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieFromDBViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieFromDBRepository()

    private val moviesFromDB = MutableLiveData<List<Movie>>()

    val moviesFromDBLiveData: LiveData<List<Movie>>
        get() = moviesFromDB

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesFromDB.postValue(repository.getMovies())
        }
    }
}