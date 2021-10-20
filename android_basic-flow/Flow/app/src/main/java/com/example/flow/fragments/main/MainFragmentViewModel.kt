package com.example.flow.fragments.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.flow.MovieType
import com.example.flow.db.movies.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var job: Job

    private val repository = MainFragmentRepository()

    private val movies = MutableLiveData<List<Movie>>()

    val movieLiveData: LiveData<List<Movie>>
        get() = movies

    suspend fun bind(queryFlow: Flow<String>, movieTypeFlow: Flow<MovieType>) {
        job = combine(queryFlow, movieTypeFlow) { query, type -> query to type }
            .debounce(500)
            .mapLatest { (movieName, movieType) ->
                repository.searchMovies(movieName, movieType)
            }
            .onEach { movies.postValue(it) }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}