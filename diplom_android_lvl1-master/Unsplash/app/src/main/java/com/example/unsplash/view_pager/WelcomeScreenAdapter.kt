package com.example.unsplash.view_pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.unsplash.R

class WelcomeScreenAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val textList: List<String> = listOf(
        fragment.resources.getString(R.string.first_welcome_text),
        fragment.resources.getString(R.string.second_welcome_text),
        fragment.resources.getString(R.string.third_welcome_text)
    )

    override fun getItemCount(): Int {
        return textList.size
    }

    override fun createFragment(position: Int): Fragment {
        val text = textList[position]
        return ViewPagerFragment.newInstance(text)
    }

}