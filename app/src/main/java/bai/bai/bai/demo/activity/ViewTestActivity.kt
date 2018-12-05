package bai.bai.bai.demo.activity

import android.animation.ObjectAnimator
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.*
import bai.bai.bai.demo.R
import bai.bai.bai.demo.anim.MyInterpolator
import kotlinx.android.synthetic.main.activity_view_test.*

/**
 * 测试
 */
class ViewTestActivity : AppCompatActivity() {

    var lastX: Float = 0F
    var lastY: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_test)

        iv_test.startAnimation(rotateAnim(this, R.anim.base_loading))
    }

    /**
     * 旋转动画
     */
    fun rotateAnim(context: Context, drawableId: Int): Animation {
//        val rotateAnim = AnimationUtils.loadAnimation(context, drawableId)
//        rotateAnim.interpolator = MyInterpolator()//自己的插值器
//        return rotateAnim

        val obj = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        obj.interpolator = LinearInterpolator()
        obj.duration = 700
        obj.repeatCount = -1
        obj.startOffset = 200//执行前的等待时间
        return obj
    }

}
