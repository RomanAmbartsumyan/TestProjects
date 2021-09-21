package com.example.unsplash.view_pager.tranfomation

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs


class DepthTransformation : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        when {
            position < -1 -> {
                page.alpha = 0F
            }
            position <= 0 -> {
                page.alpha = 1F
                page.translationX = 0F
                page.scaleX = 1F
                page.scaleY = 1F
            }
            position <= 1 -> {
                page.translationX = -position * page.width
                page.alpha = 1 - abs(position)
                page.scaleX = 1 - abs(position)
                page.scaleY = 1 - abs(position)
            }
            else -> {
                page.alpha = 0F
            }
        }
    }
}