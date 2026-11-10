package com.mardous.booming.ui.component.transform

import android.view.View
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class HingeTransformation : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            translationX = -position * width
            pivotX = 0f
            pivotY = 0f
            when {
                position < -1 -> {
                    alpha = 0f
                    isVisible = false
                }
                position <= 0 -> {
                    rotation = 90 * abs(position)
                    alpha = 1 - abs(position)
                    isVisible = true
                }
                position <= 1 -> {
                    rotation = 0f
                    alpha = 1f
                    isVisible = true
                }
                else -> {
                    alpha = 0f
                    isVisible = false
                }
            }
        }
    }
}
