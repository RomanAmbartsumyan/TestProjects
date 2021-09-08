package com.example.testapp.fragments.main

import android.os.Build
import com.example.testapp.connection.Api
import com.example.testapp.objects.Card
import com.example.testapp.utils.formatDate
import com.example.testapp.utils.formatDateForApi21
import javax.inject.Inject

class MainFragmentRepositoryImpl @Inject constructor(private val api: Api) :
    MainFragmentRepository {

    override suspend fun getPosts(): List<Card> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            api.getPosts().map { card -> formatDate(card) }
        } else {
            api.getPosts().map { card -> formatDateForApi21(card) }
        }
    }
}