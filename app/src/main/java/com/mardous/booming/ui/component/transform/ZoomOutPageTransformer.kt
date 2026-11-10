package com.mardous.booming.ui.component.transform

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs
import kotlin.math.max

class ZoomOutPageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        page.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> {
                    alpha = 1f
                    scaleY = 0.7f
                }
                position <= 1 -> {
                    val scaleFactor = max(MIN_SCALE, 1 - abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        -horzMargin + vertMargin / 2
                    }
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> {
                    alpha = 1f
                    scaleY = 0.7f
                }
            }
        }
    }

    companion object {
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }
}
