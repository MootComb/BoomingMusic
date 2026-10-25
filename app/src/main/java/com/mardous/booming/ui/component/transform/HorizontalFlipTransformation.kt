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

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class HorizontalFlipTransformation : ViewPager2.PageTransformer {
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
