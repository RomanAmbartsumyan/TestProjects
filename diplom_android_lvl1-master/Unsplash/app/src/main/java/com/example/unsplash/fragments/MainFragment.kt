package com.example.unsplash.fragments

import android.os.Bundle
import android.view.View
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.unsplash.R
import com.example.unsplash.data_store.DataStoreKeys
import com.example.unsplash.utils.getResultFromDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            val isWelcomeScreenSowedKey =
                booleanPreferencesKey(DataStoreKeys.IS_WELCOME_SCREEN_SHOWED)

            val isWelcomeScreenShowed = getResultFromDataStore(isWelcomeScreenSowedKey) ?: false

            val action = if (isWelcomeScreenShowed) {
                MainFragmentDirections.actionMainFragmentToEnterFragment()
            } else {
                MainFragmentDirections.actionMainFragmentToOnBoardingFragment()
            }
            findNavController().navigate(action)
        }
    }
}