package com.example.module12.objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(var id: Long, val url: String) : Parcelable