package com.mardous.booming.ui.component.transform

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class DepthTransformation : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 0 -> {
                    alpha = 1f
                    translationX = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> {
                    alpha = 1 - position
                    translationX = width * -position
                    val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }

    companion object {
        private const val MIN_SCALE = 0.5f
    }
}
