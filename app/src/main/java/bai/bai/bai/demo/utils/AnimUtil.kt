package bai.bai.bai.demo.utils

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.view.animation.*
import android.widget.ImageView
import bai.bai.bai.demo.view.MyAnimation

//region 补间动画

/**
 * 平移动画
 */
fun Translate(view : View){
    val translate = TranslateAnimation(2,0.2f,2,1f,2,0.2f,2,1f)
    translate.duration = 1000
    view.startAnimation(translate)
}
/**
 * 缩放动画
 */
fun myScale(view : View){
    val translate = ScaleAnimation(0.0f, 1.0f, 1.0f, 0.0f)
    translate.duration = 1000
    translate.interpolator = LinearInterpolator()
    view.startAnimation(translate)
}

/**
 * 旋转动画
 */
fun myRotate(view : View){
    val rotate = RotateAnimation(0.0f, 270f, 0.0f, 0.0f)
    rotate.duration = 1000
    rotate.interpolator = LinearInterpolator()
    view.startAnimation(rotate)
}
/**
 * 透明度动画
 */
fun myAlpha(view : View){
    val rotate = AlphaAnimation(0.0f, 1.0f)
    rotate.duration = 1000
    rotate.interpolator = LinearInterpolator()
    view.startAnimation(rotate)
}

/**
 * 旋转动画(围绕中心点)
 */
private fun rotateAnim(context: Context, drawableId: Int): Animation {
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

/**
 * 旋转动画（围绕中心线旋转）
 */
private fun rotateAnim2(view : View){
    val rotateYAnim = MyAnimation("X")
    rotateYAnim.duration = 3000
    view.startAnimation(rotateYAnim)
}

//endregion

//region 帧动画

/**
 * 帧动画
 */
fun drawableAnim(imageView: ImageView, drawableId: Int) {
    imageView.setBackgroundResource(drawableId)
    val mAnimationDrawable = imageView.background as AnimationDrawable
    mAnimationDrawable.start()
}

//endregion

//region 属性动画
/**
 * 控件的上下平移动画
 */
fun transAnimViewVertical(view: View, fromLocation: Float, toLocation: Float) {
    val animator = ObjectAnimator.ofFloat(view, "translationY", fromLocation, toLocation)
    animator.duration = 1000
    animator.start()
}
//endregion