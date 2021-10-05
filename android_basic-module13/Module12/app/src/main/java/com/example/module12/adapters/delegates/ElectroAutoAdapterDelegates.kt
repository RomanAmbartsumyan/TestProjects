package com.example.module12.adapters.delegates

import android.view.View
import android.view.ViewGroup
import com.example.module12.R
import com.example.module12.adapters.BaseAutoHolder
import com.example.module12.extentions.inflate
import com.example.module12.objects.Auto
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.item_electro_car.*

class ElectroAutoAdapterDelegates(private val onItemClicked: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Auto.ElectroCar, Auto, ElectroAutoAdapterDelegates.ElectroCarHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ElectroCarHolder {
        return ElectroCarHolder(parent.inflate(R.layout.item_electro_car), onItemClicked)
    }

    override fun isForViewType(item: Auto, items: MutableList<Auto>, position: Int): Boolean {
        return item is Auto.ElectroCar
    }

    override fun onBindViewHolder(
        item: Auto.ElectroCar,
        holder: ElectroCarHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ElectroCarHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : BaseAutoHolder(view, onItemClicked) {

        fun bind(auto: Auto.ElectroCar) {
            bindMainInfo(auto.image, auto.brand, auto.model)
            textViewElectroCar.text = auto.type
        }
    }
}