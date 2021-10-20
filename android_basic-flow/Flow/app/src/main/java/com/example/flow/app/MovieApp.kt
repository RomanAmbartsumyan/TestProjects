package com.example.flow.app

import android.app.Application
import com.example.flow.db.Database

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Database.init(this)
    }
}