package com.example.testapp.store

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.testapp.store.DataStoreKeys.DATA_STORE_NAME

val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)