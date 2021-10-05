package com.example.module12.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.module12.extentions.fromDpToPixels

class ItemOffsetDecoration(private val context: Context) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val offset = 8.fromDpToPixels(context)
        with(outRect){
            left = offset
            right = offset
            top = offset
            bottom = offset
        }

    }
}