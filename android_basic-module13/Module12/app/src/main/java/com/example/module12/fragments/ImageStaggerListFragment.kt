package com.example.module12.fragments

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.module12.R
import com.example.module12.adapters.ImageAdapter
import kotlinx.android.synthetic.main.fragment_image_for_stagger.*

class ImageStaggerListFragment : AbstractFragmentList() {
    override fun clickOnButton() {
        addFabForStagger.setOnClickListener {
            addNewImage()
        }
    }

    override fun initList() {
        imageAdapter =
            ImageAdapter(R.layout.item_image_for_stagger) { position -> deleteImage(position) }
        with(imageStaggerRecycleView) {
            imageAdapter.items = images
            adapter = imageAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)

            val dividerItemDecorationVertical =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            val dividerItemDecorationHorizontal =
                DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
            addItemDecoration(dividerItemDecorationVertical)
            addItemDecoration(dividerItemDecorationHorizontal)
        }
    }
}