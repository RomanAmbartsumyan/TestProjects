package com.example.testapp.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.databinding.ItemCardBinding
import com.example.testapp.objects.Card
import com.example.testapp.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class CardDelegate(private val info: (id: Int) -> Unit) :
    AbsListItemAdapterDelegate<Card, Card, CardDelegate.Holder>() {

    override fun isForViewType(item: Card, items: MutableList<Card>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(ItemCardBinding::inflate), info)
    }

    override fun onBindViewHolder(item: Card, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }


    class Holder(
        private val binding: ItemCardBinding,
        info: (id: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.layout.setOnClickListener {
                info(absoluteAdapterPosition)
            }
        }

        fun bind(card: Card) {
            with(binding) {
                titleText.text = card.title
                detailText.text = card.text
                date.text = card.date.toString()


                Glide.with(itemView)
                    .load(card.image)
                    .error(R.drawable.ic_error)
                    .into(binding.image)
            }
        }
    }
}