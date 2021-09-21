package com.example.unsplash.fragments

import android.os.Bundle
import android.view.View
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unsplash.R
import com.example.unsplash.data_store.DataStoreKeys.IS_WELCOME_SCREEN_SHOWED
import com.example.unsplash.databinding.FragmentWelcomeBinding
import com.example.unsplash.utils.onBackPressedExit
import com.example.unsplash.utils.saveKeyToDataStore
import com.example.unsplash.view_pager.WelcomeScreenAdapter
import com.example.unsplash.view_pager.tranfomation.DepthTransformation
import kotlinx.coroutines.launch

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val binding: FragmentWelcomeBinding by viewBinding()

    private var pageNumber: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAndButtonsFunctions()
        onBackPressedExit()
    }

    private fun viewPagerAndButtonsFunctions() {
        val adapter = WelcomeScreenAdapter(requireParentFragment())
        with(binding) {
            viewPager.adapter = adapter
            viewPager.setPageTransformer(DepthTransformation())

            imageViewNext.setOnClickListener {
                if (pageNumber <= 2) {
                    ++pageNumber
                }
                viewPager.currentItem = pageNumber

                if (pageNumber == 3) {
                    lifecycleScope.launch {
                        val key = booleanPreferencesKey(IS_WELCOME_SCREEN_SHOWED)
                        saveKeyToDataStore(key, true)
                    }
                    val action = WelcomeFragmentDirections.actionOnBoardingFragmentToEnterFragment()
                    findNavController().navigate(action)
                }
            }

            imageViewBack.setOnClickListener {
                if (pageNumber > 0) {
                    --pageNumber
                }
                viewPager.currentItem = pageNumber
            }

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    pageNumber = position
                    if (position == 0) {
                        imageViewBack.visibility = View.GONE
                    } else {
                        imageViewBack.visibility = View.VISIBLE
                    }
                }
            })
        }
    }
}