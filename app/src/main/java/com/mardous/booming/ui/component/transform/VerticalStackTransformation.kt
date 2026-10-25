package com.mardous.booming.ui.component.transform

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class VerticalStackTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            if (position >= 0) {
                scaleX = (0.9f - 0.05f * position)
                scaleY = 0.9f
                translationX = -width * position
                translationY = -30 * position
            }
        }
    }
}
