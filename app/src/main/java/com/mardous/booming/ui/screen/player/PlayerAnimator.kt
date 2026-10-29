/*
 * Copyright (c) 2024 Christians Martínez Alvarado
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mardous.booming.ui.screen.player

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.view.View
import androidx.core.animation.doOnStart
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import java.util.LinkedList

abstract class PlayerAnimator(isEnabled: Boolean) {
    private var musicControllerAnimationSet: AnimatorSet? = null

    var isEnabled: Boolean = isEnabled
        set(value) {
            field = value
            if (!value) {
                startInternal(true)
            } else {
                prepare()
            }
        }

    var isPrepared = false
        private set

    fun start() {
        startInternal(false)
    }

    fun prepare() {
        if (isEnabled) {
            onPrepareForAnimation()

            musicControllerAnimationSet?.cancel()
            isPrepared = true
        }
    }

    private fun startInternal(isForced: Boolean) {
        if (!isPrepared && !isForced) return

        if (musicControllerAnimationSet == null) {
            val interpolator = FastOutSlowInInterpolator()
            val animators = LinkedList<Animator>()

            onAddAnimation(animators, interpolator)

            musicControllerAnimationSet = AnimatorSet().apply {
                doOnStart {
                    onAnimationStarted()
                }
                playTogether(animators)
            }
        } else {
            musicControllerAnimationSet!!.cancel()
        }

        if (isEnabled || isForced) {
            musicControllerAnimationSet!!.start()
            isPrepared = false
        }
    }

    protected fun addScaleAnimation(
        animators: MutableCollection<Animator>,
        view: View,
        interpolator: TimeInterpolator?,
        @Suppress("UNUSED_PARAMETER") delay: Int
    ) {
        val scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 1f).apply {
            this.interpolator = interpolator
            this.duration = 1L
            this.startDelay = 0L
        }
        animators.add(scaleX)

        val scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 1f).apply {
            this.interpolator = interpolator
            this.duration = 1L
            this.startDelay = 0L
        }
        animators.add(scaleY)
    }

    protected fun prepareForScaleAnimation(view: View?) {
        view?.scaleX = 1f
        view?.scaleY = 1f
    }

    open fun onAnimationStarted() {}

    open fun onAddAnimation(animators: LinkedList<Animator>, interpolator: TimeInterpolator) {}
    open fun onPrepareForAnimation() {}
}
