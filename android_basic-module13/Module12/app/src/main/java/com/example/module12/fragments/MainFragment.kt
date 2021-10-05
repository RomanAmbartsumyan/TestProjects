package com.example.module12.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.module12.R
import com.example.module12.fragments.interfaces.MainFragmentInterface

class MainFragment : Fragment(R.layout.fragment_main), MainFragmentInterface {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) {
            childFragmentManager
                .beginTransaction()
                .replace(R.id.mainContainer, FragmentButtons())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun addFragmentAutoList() {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, AutoFragmentList())
            .addToBackStack(null).commit()
    }

    override fun addFragmentLinerLayoutManager() {
        childFragmentManager
            .beginTransaction()
            .replace(
                R.id.mainContainer,
                AbstractFragmentList.newInstance(
                    R.layout.fragment_image_for_horizontal,
                    ImageHorisontalListFragment()
                )
            )
            .addToBackStack(null).commit()
    }

    override fun addFragmentGridLayoutManager() {
        childFragmentManager
            .beginTransaction()
            .replace(
                R.id.mainContainer, AbstractFragmentList.newInstance(
                    R.layout.fragment_image_for_grid,
                    ImageGridListFragment()
                )
            )
            .addToBackStack(null).commit()
    }

    override fun addFragmentStaggerLayoutManager() {
        childFragmentManager
            .beginTransaction()
            .replace(
                R.id.mainContainer, AbstractFragmentList.newInstance(
                    R.layout.fragment_image_for_stagger,
                    ImageStaggerListFragment()
                )
            )
            .addToBackStack(null).commit()
    }
}