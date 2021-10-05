package com.example.module12.extentions

import android.content.Context
import android.util.DisplayMetrics

fun Int.fromDpToPixels(context: Context): Int {
    val density = context.resources.displayMetrics.densityDpi
    val pixelsInDp = density / DisplayMetrics.DENSITY_DEFAULT
    return this * pixelsInDp
}