package com.example.module12.fragments

import androidx.recyclerview.widget.GridLayoutManager
import com.example.module12.R
import com.example.module12.adapters.ImageAdapter
import com.example.module12.decoration.ItemOffsetDecoration
import kotlinx.android.synthetic.main.fragment_image_for_grid.*

class ImageGridListFragment : AbstractFragmentList() {
    override fun clickOnButton() {
        addFabForGrid.setOnClickListener {
            addNewImage()
        }
    }

    override fun initList() {
        imageAdapter =
            ImageAdapter(R.layout.item_image_for_grid) { position -> deleteImage(position) }
        with(imageGridRecycleView) {
            imageAdapter.items = images
            imageAdapter.notifyDataSetChanged()
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            setHasFixedSize(true)
            addItemDecoration(ItemOffsetDecoration(requireContext()))
        }
    }
}