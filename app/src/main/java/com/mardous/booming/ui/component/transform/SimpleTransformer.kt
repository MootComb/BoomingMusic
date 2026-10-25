package com.mardous.booming.ui.component.transform

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class SimplePageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        // no-op: page remains in place, no scaling or translation
    }
}
