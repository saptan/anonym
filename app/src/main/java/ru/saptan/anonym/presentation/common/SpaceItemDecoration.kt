package ru.saptan.anonym.presentation.common

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View

class SpaceItemDecoration(
        val context: Context,
        verticalSpaceHeight: Float = 8F,
        horizontalSpaceWidth: Float = 16F
) : RecyclerView.ItemDecoration() {

    var spaceHeight: Int = 0
    var spaceWidth: Int = 0

    init {
        val metrics = context.resources.displayMetrics
        spaceHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, verticalSpaceHeight, metrics).toInt()
        spaceWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, horizontalSpaceWidth, metrics).toInt()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        outRect.top = spaceHeight
        outRect.left = spaceWidth
        outRect.right = spaceWidth
        outRect.bottom = spaceHeight
    }

}