package com.example.testapp.fragments.main

import com.example.testapp.objects.Card

interface MainFragmentRepository {
    suspend fun getPosts(): List<Card>
}