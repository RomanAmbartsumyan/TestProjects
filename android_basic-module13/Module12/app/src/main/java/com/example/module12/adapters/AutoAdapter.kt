package com.example.module12.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.module12.adapters.delegates.ElectroAutoAdapterDelegates
import com.example.module12.adapters.delegates.UsualAutoAdapterDelegates
import com.example.module12.objects.Auto
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AutoAdapter(onItemClicked: (position: Int) -> Unit) :
    AsyncListDifferDelegationAdapter<Auto>(AutoDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(ElectroAutoAdapterDelegates(onItemClicked))
            .addDelegate(UsualAutoAdapterDelegates(onItemClicked))
    }

    class AutoDiffUtilCallback : DiffUtil.ItemCallback<Auto>() {
        override fun areItemsTheSame(oldItem: Auto, newItem: Auto): Boolean {
            return when {
                oldItem is Auto.UsualCar && newItem is Auto.UsualCar -> oldItem.id == newItem.id
                oldItem is Auto.ElectroCar && newItem is Auto.ElectroCar -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Auto, newItem: Auto): Boolean {
            return oldItem == newItem
        }
    }
}