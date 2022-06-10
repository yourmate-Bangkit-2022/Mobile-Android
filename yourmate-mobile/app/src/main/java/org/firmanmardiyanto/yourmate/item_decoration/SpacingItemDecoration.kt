package org.firmanmardiyanto.yourmate.item_decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    private val space: Int = 32,
    private val orientation: ORIENTATION = ORIENTATION.VERTICAL
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (orientation) {
            ORIENTATION.VERTICAL -> {
                outRect.left = space
                outRect.right = space
                outRect.bottom = space

                // Add top margin only for the first item to avoid double space between items
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = space
                }
            }
            ORIENTATION.HORIZONTAL -> {
                outRect.top = space
                outRect.bottom = space
                outRect.right = space

                // Add left margin only for the first item to avoid double space between items
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = space
                }
            }
        }
    }

    enum class ORIENTATION {
        VERTICAL,
        HORIZONTAL
    }
}