package com.example.module12.adapters.delegates

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module12.R
import com.example.module12.extentions.inflate
import com.example.module12.objects.Image
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_car.*

class ImageDelegates(
    @LayoutRes val layout: Int,
    private val onItemClicked: (position: Int) -> Unit
) :
    AbsListItemAdapterDelegate<Image, Image, ImageDelegates.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(layout), onItemClicked)
    }

    override fun isForViewType(item: Image, items: MutableList<Image>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(item: Image, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class Holder(
        override val containerView: View,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(imageUrl: Image) {
            Glide.with(itemView)
                .load(imageUrl.url)
                .placeholder(R.drawable.ic_car)
                .error(R.drawable.ic_error)
                .into(imageViewPicture)
        }
    }
}