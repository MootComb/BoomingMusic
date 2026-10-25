/*
 * Copyright (c) 2019 Hemanth Savarala.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by
 *  the Free Software Foundation either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.mardous.booming.ui.component.transform

import android.content.Context
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 * Carousel effect adapted for [ViewPager2].
 *
 * [ViewPager2] reports [position] as:
 *  0  -> current page
 *  1  -> next page
 * -1  -> previous page
 * so we can derive offset/scale directly from it instead of reading scrollX.
 */
class CarouselPagerTransformer(context: Context) : ViewPager2.PageTransformer {

    private val maxTranslateOffsetX: Int = dp2px(context, 180f)

    override fun transformPage(view: View, position: Float) {
        val offsetRate = position * 0.30f
        val scaleFactor = 1 - abs(offsetRate)
        if (scaleFactor > 0f) {
            view.scaleX = scaleFactor
            view.scaleY = scaleFactor
            view.translationX = -maxTranslateOffsetX * offsetRate
        } else {
            view.scaleX = 1f
            view.scaleY = 1f
            view.translationX = 0f
        }
    }

    private fun dp2px(context: Context, dipValue: Float): Int {
        val m = context.resources.displayMetrics.density
        return (dipValue * m + 0.5f).toInt()
    }
}
