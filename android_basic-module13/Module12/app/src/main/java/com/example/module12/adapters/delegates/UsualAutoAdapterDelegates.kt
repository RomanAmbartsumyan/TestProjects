package com.example.module12.adapters.delegates

import android.view.View
import android.view.ViewGroup
import com.example.module12.R
import com.example.module12.adapters.BaseAutoHolder
import com.example.module12.extentions.inflate
import com.example.module12.objects.Auto
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class UsualAutoAdapterDelegates(private val onItemClicked: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Auto.UsualCar, Auto, UsualAutoAdapterDelegates.UsualCarHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): UsualCarHolder {
        return UsualCarHolder(parent.inflate(R.layout.item_car), onItemClicked)
    }

    override fun isForViewType(item: Auto, items: MutableList<Auto>, position: Int): Boolean {
        return item is Auto.UsualCar
    }

    override fun onBindViewHolder(
        item: Auto.UsualCar,
        holder: UsualCarHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class UsualCarHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : BaseAutoHolder(view, onItemClicked) {

        fun bind(auto: Auto.UsualCar) {
            bindMainInfo(auto.image, auto.brand, auto.model)
        }
    }
}