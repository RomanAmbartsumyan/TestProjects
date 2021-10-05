package com.example.module12.adapters

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import com.example.module12.adapters.delegates.ImageDelegates
import com.example.module12.objects.Image
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ImageAdapter(@LayoutRes val layout: Int, onItemClicked: (position: Int) -> Unit) :
    AsyncListDifferDelegationAdapter<Image>(ImageDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(ImageDelegates(layout, onItemClicked))
    }

    class ImageDiffUtilCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }
}