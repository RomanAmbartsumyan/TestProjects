package com.example.flow.utils

import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.flow.MovieType
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map


fun RadioGroup.toMovieType(): Flow<MovieType> {
    return callbackFlow {
        val checkedChangeListener = RadioGroup.OnCheckedChangeListener { _, p1 ->
            trySendBlocking(findViewById<RadioButton>(p1))
        }
        this@toMovieType.setOnCheckedChangeListener(checkedChangeListener)
        awaitClose {
            this@toMovieType.setOnCheckedChangeListener(null)
        }
    }.map {
        when (it.text) {
            "movie" -> MovieType.MOVIE
            "series" -> MovieType.SERIES
            "episode" -> MovieType.EPISODE
            else -> MovieType.MOVIE
        }
    }
}
