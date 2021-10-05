package com.example.module12.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.module12.R
import com.example.module12.fragments.interfaces.MainFragmentInterface
import kotlinx.android.synthetic.main.fragment_buttons.*

class FragmentButtons : Fragment(R.layout.fragment_buttons) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonAutoList.setOnClickListener {
            (parentFragment as MainFragmentInterface).addFragmentAutoList()
        }
        buttonLinearLayoutManager.setOnClickListener {
            (parentFragment as MainFragmentInterface).addFragmentLinerLayoutManager()
        }

        buttonGridLayoutManager.setOnClickListener {
            (parentFragment as MainFragmentInterface).addFragmentGridLayoutManager()
        }

        buttonStaggeredGridLayoutManager.setOnClickListener {
            (parentFragment as MainFragmentInterface).addFragmentStaggerLayoutManager()
        }
    }
}