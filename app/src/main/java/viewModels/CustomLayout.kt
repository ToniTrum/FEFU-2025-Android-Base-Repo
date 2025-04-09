package viewModels

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class CustomLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var currentWidth = 0
        var currentHeight = 0
        var maxWidth = 0
        var totalHeight = 0

        val childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.AT_MOST)
        val childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, childWidthSpec, childHeightSpec)

            if (currentWidth + child.measuredWidth > widthSize) {
                totalHeight += currentHeight
                currentWidth = 0
                currentHeight = 0
            }

            currentWidth += child.measuredWidth
            currentHeight = maxOf(currentHeight, child.measuredHeight)
            maxWidth = maxOf(maxWidth, currentWidth)
        }

        totalHeight += currentHeight

        val finalWidth = if (widthMode == MeasureSpec.EXACTLY) widthSize else maxWidth
        val finalHeight = if (heightMode == MeasureSpec.EXACTLY) MeasureSpec.getSize(heightMeasureSpec) else totalHeight

        setMeasuredDimension(finalWidth, finalHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var x = 0
        var y = 0
        var rowHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)

            if (x + child.measuredWidth > width) {
                x = 0
                y += rowHeight
                rowHeight = 0
            }

            child.layout(x, y, x + child.measuredWidth, y + child.measuredHeight)

            x += child.measuredWidth
            rowHeight = maxOf(rowHeight, child.measuredHeight)
        }
    }
}