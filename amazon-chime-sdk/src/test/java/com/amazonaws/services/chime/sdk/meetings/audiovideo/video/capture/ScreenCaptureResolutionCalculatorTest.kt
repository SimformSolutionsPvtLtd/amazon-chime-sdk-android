/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.amazonaws.services.chime.sdk.meetings.audiovideo.video.capture

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import org.junit.Assert.assertTrue
import org.junit.Test

class ScreenCaptureResolutionCalculatorTest {

    private val aspectRatioDiffThreshold: Int = 12
    private val targetMinVal: Int = 1080
    private val targetMaxVal: Int = 1920
    private val screenCaptureResolutionCalculator: ScreenCaptureResolutionCalculator = ScreenCaptureResolutionCalculator(this.targetMinVal, this.targetMaxVal)

    @Test
    fun `ScreenCaptureResolutionCalculator (landscape) should return original resolution when max(width, height) is below targetMaxVal and min(width, height) is below targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1280
        val height: Int = 720
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val originalAlignedWidth: Int = width and 1.inv()
        val originalAlignedHeight: Int = height and 1.inv()
        assertTrue(alignedWidth == originalAlignedWidth && alignedHeight == originalAlignedHeight)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (landscape) should return original resolution when max(width, height) is equal targetMaxVal and min(width, height) is below targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1920
        val height: Int = 719
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val originalAlignedWidth: Int = width and 1.inv()
        val originalAlignedHeight: Int = height and 1.inv()
        assertTrue(alignedWidth == originalAlignedWidth && alignedHeight == originalAlignedHeight)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (landscape) should return original resolution when max(width, height) is below targetMaxVal and min(width, height) is equal targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1280
        val height: Int = 1080
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val originalAlignedWidth: Int = width and 1.inv()
        val originalAlignedHeight: Int = height and 1.inv()
        assertTrue(alignedWidth == originalAlignedWidth && alignedHeight == originalAlignedHeight)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (landscape) should return original resolution when max(width, height) is equal targetMaxVal and min(width, height) is equal targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1920
        val height: Int = 1080
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val originalAlignedWidth: Int = width and 1.inv()
        val originalAlignedHeight: Int = height and 1.inv()
        assertTrue(alignedWidth == originalAlignedWidth && alignedHeight == originalAlignedHeight)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (weird number) should return scaled resolution when max(width, height) is above targetMaxVal and min(width, height) is above targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 7139
        val height: Int = 3217
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (extra large number) should return scaled resolution when max(width, height) is above targetMaxVal and min(width, height) is above targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int =  93215327
        val height: Int = 32171121
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (landscape) should return scaled resolution when max(width, height) is above targetMaxVal and min(width, height) is above targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 3840
        val height: Int = 1423
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (landscape) should return scaled resolution when max(width, height) is above targetMaxVal and min(width, height) is below targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 3840
        val height: Int = 720
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (landscape) should return scaled resolution when max(width, height) is above targetMaxVal and min(width, height) is equal targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 3840
        val height: Int = 1080
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (landscape) should return scaled resolution when max(width, height) is equal targetMaxVal and min(width, height) is above targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1920
        val height: Int = 1280
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (landscape) should return scaled resolution when max(width, height) is below targetMaxVal and min(width, height) is above targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1600
        val height: Int = 1280
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }



    @Test
    fun `ScreenCaptureResolutionCalculator (portrait) should return original resolution when max(width, height) is below targetMaxVal and min(width, height) is below targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 720
        val height: Int = 1280
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val originalAlignedWidth: Int = width and 1.inv()
        val originalAlignedHeight: Int = height and 1.inv()
        assertTrue(alignedWidth == originalAlignedWidth && alignedHeight == originalAlignedHeight)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (portrait) should return original resolution when max(width, height) is equal targetMaxVal and min(width, height) is below targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 719
        val height: Int = 1920
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val originalAlignedWidth: Int = width and 1.inv()
        val originalAlignedHeight: Int = height and 1.inv()
        assertTrue(alignedWidth == originalAlignedWidth && alignedHeight == originalAlignedHeight)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (portrait) should return original resolution when max(width, height) is below targetMaxVal and min(width, height) is equal targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1080
        val height: Int = 1280
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val originalAlignedWidth: Int = width and 1.inv()
        val originalAlignedHeight: Int = height and 1.inv()
        assertTrue(alignedWidth == originalAlignedWidth && alignedHeight == originalAlignedHeight)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (portrait) should return original resolution when max(width, height) is equal targetMaxVal and min(width, height) is equal targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1080
        val height: Int = 1920
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val originalAlignedWidth: Int = width and 1.inv()
        val originalAlignedHeight: Int = height and 1.inv()
        assertTrue(alignedWidth == originalAlignedWidth && alignedHeight == originalAlignedHeight)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (portrait) should return scaled resolution when max(width, height) is above targetMaxVal and min(width, height) is above targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1423
        val height: Int = 3840
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (portrait) should return scaled resolution when max(width, height) is above targetMaxVal and min(width, height) is below targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 720
        val height: Int = 3840
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (portrait) should return scaled resolution when max(width, height) is above targetMaxVal and min(width, height) is equal targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1080
        val height: Int = 3840
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (portrait) should return scaled resolution when max(width, height) is equal targetMaxVal and min(width, height) is above targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1280
        val height: Int = 1920
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }

    @Test
    fun `ScreenCaptureResolutionCalculator (portrait) should return scaled resolution when max(width, height) is below targetMaxVal and min(width, height) is above targetMinVal`() {
        // compute targetWidth and targetHeight with alignment
        val width: Int = 1280
        val height: Int = 1600
        val targetSize = screenCaptureResolutionCalculator.computeTargetSize(width, height)
        val alignedWidth: Int = targetSize[0]
        val alignedHeight: Int = targetSize[1]
        val maxVal: Int = max(alignedWidth, alignedHeight)
        val minVal: Int = min(alignedWidth, alignedHeight)
        val originalAspectRatio: Int = (1000 * width.toDouble() / height.toDouble()).toInt()
        val scaledAspectRatio: Int = (1000 * alignedWidth.toDouble() / alignedHeight.toDouble()).toInt()
        val aspectRatioDiff: Int = abs(scaledAspectRatio - originalAspectRatio)
        assert(minVal <= targetMinVal && maxVal <= targetMaxVal && aspectRatioDiff < aspectRatioDiffThreshold)
    }
}