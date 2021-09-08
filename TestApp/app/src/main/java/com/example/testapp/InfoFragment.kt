package com.example.testapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.testapp.databinding.FragmentInfoBinding

class InfoFragment : Fragment(R.layout.fragment_info) {
    private val binding: FragmentInfoBinding by viewBinding()
    private val args: InfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val card = args.card

        with(binding) {
            textTitle.text = card.title
            textDetail.text = card.text
            Glide.with(requireContext())
                .load(card.image)
                .error(R.drawable.ic_error)
                .into(imageViewInfo)
        }
    }

}