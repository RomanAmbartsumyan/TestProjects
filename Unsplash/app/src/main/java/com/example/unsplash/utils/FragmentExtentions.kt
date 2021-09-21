package com.example.unsplash.utils

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import com.example.unsplash.data_store.dataStore
import kotlinx.coroutines.flow.first

fun <T : Fragment> T.withArguments(action: Bundle.() -> Unit): T {
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
}

fun Fragment.onBackPressedExit() {
    val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().finish()
        }
    }
    requireActivity().onBackPressedDispatcher.addCallback(callback)
}

suspend fun <T> Fragment.getResultFromDataStore(key: Preferences.Key<T>): T? {
    val preferences = requireContext().dataStore.data.first()
    return preferences[key]
}

suspend fun <T> Fragment.saveKeyToDataStore(key: Preferences.Key<T>, value: T) {
    requireContext().dataStore.edit { settings ->
        settings[key] = value
    }
}
