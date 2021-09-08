package com.example.testapp.utils

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import com.example.testapp.store.dataStore
import kotlinx.coroutines.flow.first

suspend fun <T> Fragment.getResultFromDataStore(key: Preferences.Key<T>): T? {
    val preferences = requireContext().dataStore.data.first()
    return preferences[key]
}

suspend fun <T> Fragment.saveKeyToDataStore(key: Preferences.Key<T>, value: T) {
    requireContext().dataStore.edit { settings ->
        settings[key] = value
    }
}
