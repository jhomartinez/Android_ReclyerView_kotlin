package com.codigoj.reclyerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TopSpacingItemDecoration (private val paddingTop: Int, private val paddingLeftAndRigth: Int): RecyclerView.ItemDecoration(){
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = paddingTop
        outRect.left = paddingLeftAndRigth
        outRect.right = paddingLeftAndRigth
    }
}