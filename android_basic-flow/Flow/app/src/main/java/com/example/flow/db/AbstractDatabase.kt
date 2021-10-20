package com.example.flow.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flow.db.movies.Movie
import com.example.flow.db.movies.MovieDao

@Database(
    entities = [Movie::class],
    version = AbstractDatabase.DB_VERSION
)
abstract class AbstractDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "database"
    }
}