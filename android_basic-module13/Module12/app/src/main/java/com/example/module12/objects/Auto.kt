package com.example.module12.objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Auto : Parcelable {
    @Parcelize
    data class UsualCar(
        val id: Long,
        val image: String,
        val brand: String,
        val model: String
    ) : Auto()

    @Parcelize
    data class ElectroCar(
        val id: Long,
        val image: String,
        val brand: String,
        val model: String,
        val type: String
    ) : Auto()
}