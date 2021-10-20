package com.example.flow.db

import android.content.Context
import androidx.room.Room

object Database {
    lateinit var instance: AbstractDatabase
        private set

    fun init(context: Context) {
        instance =
            Room.databaseBuilder(
                context,
                AbstractDatabase::class.java,
                AbstractDatabase.DB_NAME
            ).build()
    }
}