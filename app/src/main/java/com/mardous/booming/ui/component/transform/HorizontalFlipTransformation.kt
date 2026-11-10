package com.mardous.booming.ui.component.transform

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class HorizontalFlipTransformation : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            translationX = -position * width
            cameraDistance = 100000f
            if (position < 0.5 && position > -0.5) {
                isVisible = true
            } else {
                isInvisible = true
            }
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 0 -> {
                    alpha = 1f
                    rotationY = 180 * (1 - abs(position) + 1)
                }
                position <= 1 -> {
                    alpha = 1f
                    rotationY = -180 * (1 - abs(position) + 1)
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}
