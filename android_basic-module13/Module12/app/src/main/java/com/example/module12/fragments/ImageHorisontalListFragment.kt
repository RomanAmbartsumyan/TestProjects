package com.example.module12.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module12.R
import com.example.module12.adapters.ImageAdapter
import kotlinx.android.synthetic.main.fragment_image_for_horizontal.*

class ImageHorisontalListFragment : AbstractFragmentList() {

    override fun clickOnButton() {
        addFabForHorizontal.setOnClickListener {
            addNewImage()
        }
    }

    override fun initList() {
        imageAdapter =
            ImageAdapter(R.layout.item_image_for_horizontal) { position -> deleteImage(position) }
        with(imageHorizontalRecycleView) {
            imageAdapter.items = images
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }
    }
}