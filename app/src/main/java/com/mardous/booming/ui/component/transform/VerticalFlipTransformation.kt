package com.mardous.booming.ui.component.transform

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class VerticalFlipTransformation : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            page.translationX = -position * page.width
            page.cameraDistance = 20000f
            if (position < 0.5 && position > -0.5) {
                page.isVisible = true
            } else {
                page.isInvisible = true
            }
            when {
                position < -1 -> {
                    page.alpha = 0f
                }
                position <= 0 -> {
                    page.alpha = 1f
                    page.rotationX = 180 * (1 - abs(position) + 1)
                }
                position <= 1 -> {
                    page.alpha = 1f
                    page.rotationX = -180 * (1 - abs(position) + 1)
                }
                else -> {
                    page.alpha = 0f
                }
            }
        }
    }
}
