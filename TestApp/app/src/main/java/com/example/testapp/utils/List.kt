package com.example.testapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.databinding.ItemCardBinding
import com.example.testapp.objects.Card
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun cardAdapter(itemClickedListener: (Card) -> Unit) = AsyncListDifferDelegationAdapter(
    DiffUtilCallback(),
    AdapterDelegatesManager(delegate { itemClickedListener(it) })
)

private fun delegate(itemClickedListener: (Card) -> Unit) =
    adapterDelegateViewBinding<Card, Card, ItemCardBinding>(
        { layoutInflater, root -> ItemCardBinding.inflate(layoutInflater, root, false) }
    ) {
        binding.layout.setOnClickListener {
            itemClickedListener(item)
        }

        bind {
            with(binding) {
                titleText.text = item.title
                detailText.text = item.text
                date.text = item.date


                Glide.with(itemView)
                    .load(item.image)
                    .error(R.drawable.ic_error)
                    .into(binding.image)
            }
        }
    }

private class DiffUtilCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}

