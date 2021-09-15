package com.example.testapp.app

import android.app.Application
import com.example.testapp.di.apiModule
import com.example.testapp.di.connectionModule
import com.example.testapp.di.repositoryModule
import com.example.testapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModule, apiModule, connectionModule, repositoryModule))
        }
    }
}