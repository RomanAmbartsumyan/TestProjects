package com.example.unsplash.view_pager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentViewPagerBinding
import com.example.unsplash.utils.onBackPressedExit
import com.example.unsplash.utils.withArguments

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {
    private val binding: FragmentViewPagerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressedExit()
        binding.textViewInfo.text = requireArguments().getString(KEY_TEXT)
    }

    companion object {
        private const val KEY_TEXT = "KEY_TEXT"

        fun newInstance(text: String): ViewPagerFragment {
            return ViewPagerFragment().withArguments {
                putString(KEY_TEXT, text)
            }
        }
    }
}