package bai.bai.bai.demo.view

import android.graphics.Camera
import android.graphics.Matrix
import android.os.Parcel
import android.os.Parcelable
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.Transformation

class MyAnimation : Animation {

    //对称轴
    val ROTATE_AXIS_X = "X"
    val ROTATE_AXIS_Y = "Y"
    val ROTATE_AXIS_Z = "Z"

    private var mRotateAxis: String
    private var centerX: Int = 0
    private var centerY: Int = 0
    private var camera = Camera()

    constructor(axis: String = "Y") {
        mRotateAxis = axis
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        //获取中心点坐标
        centerX = width / 2
        centerY = height / 2
        //动画执行时间  自行定义
        repeatCount = Animation.INFINITE
        interpolator = LinearInterpolator()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val matrix = t.matrix
        camera.save()
        //中心是绕Y轴旋转  这里可以自行设置X轴 Y轴 Z轴
        when (mRotateAxis) {
            ROTATE_AXIS_X -> camera.rotateX(360 * interpolatedTime)
            ROTATE_AXIS_Y -> camera.rotateY(360 * interpolatedTime)
            ROTATE_AXIS_Z -> camera.rotateZ(360 * interpolatedTime)
        }
        //把我们的摄像头加在变换矩阵上
        camera.getMatrix(matrix)
        //设置翻转中心点
        matrix.preTranslate((-centerX).toFloat(), (-centerY).toFloat())
        matrix.postTranslate(centerX.toFloat(), centerY.toFloat())
        camera.restore()
    }
}
