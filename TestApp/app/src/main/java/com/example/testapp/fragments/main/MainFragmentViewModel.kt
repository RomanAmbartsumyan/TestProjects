package com.example.testapp.fragments.main

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testapp.objects.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class MainFragmentViewModel(
    application: Application,
    private val repository: MainFragmentRepository
) :
    AndroidViewModel(application) {

    private val mutablePostsLiveData = MutableLiveData<List<Card>>()

    val postsLiveData: LiveData<List<Card>>
        get() = mutablePostsLiveData

    fun getPosts(isSortedByServer: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = if (isSortedByServer) {
                repository.getPosts().sortedBy { it.sort }
            } else {
                repository.getPosts().sortedBy { card -> date(card) }
            }
            mutablePostsLiveData.postValue(list)
        }
    }

    fun sortByServer() {
        val newList = postsLiveData.value?.sortedBy { it.sort }!!
        mutablePostsLiveData.postValue(newList)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sortByDate() {
        val newList = postsLiveData.value?.sortedBy { card ->
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss")
            LocalDateTime.parse(card.date, formatter)
        }?.reversed()!!
        mutablePostsLiveData.postValue(newList)
    }

    fun sortByDateForApi21() {
        val newList = postsLiveData.value?.sortedBy { card ->
            date(card)
        }?.reversed()!!
        mutablePostsLiveData.postValue(newList)
    }

    private fun date(card: Card): Date? {
        val formatter = SimpleDateFormat("dd-MM-yyyy, HH:mm:ss")
        return formatter.parse(card.date)
    }
}