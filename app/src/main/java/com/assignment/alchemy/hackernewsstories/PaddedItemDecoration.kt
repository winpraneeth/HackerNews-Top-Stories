package com.assignment.alchemy.hackernewsstories

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.DimenRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View

class PaddedItemDecoration(
        context: Context,
        @DimenRes startingPaddingRes: Int
) : RecyclerView.ItemDecoration() {

    private val mDivider: Drawable?
    private val dividerHeight: Int
    private val startPadding: Int

    init {
        this.mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider)
        this.dividerHeight = dpToPx(context, 1)
        this.startPadding = Math.round(context.resources.getDimension(startingPaddingRes))
    }

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        outRect.top = 0
        outRect.left = 0
        outRect.right = 0
        outRect.bottom = dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            if (i == childCount - 1) {
                mDivider.setBounds(0, top, c.width, bottom)
            } else {
                mDivider.setBounds(startPadding, top, c.width, bottom)
            }

            mDivider.draw(c)
        }


    }

    fun dpToPx(context: Context, dp: Int): Int {
        // Get the screen's density scale
        val scale = context.resources.displayMetrics.density

        // Convert the dps to pixels, based on density scale return
        return Math.ceil((dp * scale).toDouble()).toInt()
    }
}


