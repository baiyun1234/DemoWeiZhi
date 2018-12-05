package bai.bai.bai.demo.anim

import android.util.Log
import android.view.animation.LinearInterpolator

/**
 * 插值器
 */
class MyInterpolator: LinearInterpolator(){
    override fun getInterpolation(input: Float): Float {
        Log.d("baibai", "input = $input")
        return if (input > 0.0f && input < 0.714f) {
            input
        } else {
            0.0f
        }
    }
}