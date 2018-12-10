package bai.bai.bai.demo.view

import android.graphics.Camera
import android.graphics.Matrix
import android.os.Parcel
import android.os.Parcelable
import android.view.animation.*

class MyAnimation : Animation {

    //对称轴
    val ROTATE_AXIS_X = "X"
    val ROTATE_AXIS_Y = "Y"
    val ROTATE_AXIS_Z = "Z"

    private var mRotateAxis: String//旋转中心轴
    private var mInterpolator: Interpolator//插值器
    private var mDegrees: Int//旋转角度
    private val mDuration: Long
    private var centerX: Int = 0
    private var centerY: Int = 0
    private var camera = Camera()

    /**
     * 默认绕Y轴顺时针线性旋转360度，时间为1000毫秒
     */
    constructor(axis: String = "Y"
                , duretion: Long = 1000
                , interpolator: Interpolator = LinearInterpolator()
                , degrees: Int = -360) {
        mRotateAxis = axis
        mInterpolator = interpolator
        mDuration = duretion
        mDegrees = degrees
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        //获取中心点坐标
        centerX = width / 2
        centerY = height / 2
        repeatCount = Animation.INFINITE
        duration = mDuration
        interpolator = mInterpolator
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val matrix = t.matrix
        camera.save()
        //中心是绕Y轴旋转  这里可以自行设置X轴 Y轴 Z轴
        when (mRotateAxis) {
            ROTATE_AXIS_X -> camera.rotateX(mDegrees * interpolatedTime)
            ROTATE_AXIS_Y -> camera.rotateY(mDegrees * interpolatedTime)
            ROTATE_AXIS_Z -> camera.rotateZ(mDegrees * interpolatedTime)
        }
        //把我们的摄像头加在变换矩阵上
        camera.getMatrix(matrix)
        //设置翻转中心点
        matrix.preTranslate((-centerX).toFloat(), (-centerY).toFloat())
        matrix.postTranslate(centerX.toFloat(), centerY.toFloat())
        camera.restore()
    }
}
