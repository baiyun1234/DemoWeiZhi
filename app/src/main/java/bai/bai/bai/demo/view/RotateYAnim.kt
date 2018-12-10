package bai.bai.bai.demo.view

import android.graphics.Camera
import android.util.Log
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.Transformation

class RotateYAnim : Animation() {

    private var mCenterX: Int = 0
    private var mCenterY: Int = 0
    private var mCamera: Camera = Camera()
    private val mDuration: Long = 1400

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        mCenterX = width / 2
        mCenterY = height / 2
        this.duration = mDuration
        this.interpolator = LinearInterpolator()
        Log.d("baibai", "initialize()")
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val matrix = t.matrix
        mCamera.save()
        mCamera.rotateY(360 * interpolatedTime)
        mCamera.getMatrix(matrix)
        matrix?.preTranslate((-mCenterX).toFloat(), (-mCenterY).toFloat())
        matrix?.postTranslate(mCenterX.toFloat(), mCenterY.toFloat())
        mCamera.restore()
        Log.d("baibai", "applyTransformation()")
    }

}