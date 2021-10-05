package com.example.module12.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module12.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_car.*

abstract class BaseAutoHolder(
    final override val containerView: View,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        containerView.setOnClickListener {
            onItemClicked(adapterPosition)
        }
    }

    protected fun bindMainInfo(image: String, brand: String, model: String) {
        textViewBrand.text = brand
        textViewModel.text = model

        Glide.with(itemView)
            .load(image)
            .placeholder(R.drawable.ic_car)
            .error(R.drawable.ic_error)
            .into(imageViewPicture)
    }
}