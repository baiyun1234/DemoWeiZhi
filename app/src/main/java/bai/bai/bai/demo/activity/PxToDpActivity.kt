package bai.bai.bai.demo.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log

import bai.bai.bai.demo.R
import kotlinx.android.synthetic.main.activity_px_to_dp.*

class PxToDpActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_px_to_dp)

        btn_start.setOnClickListener {
            var dp = px2dip(this, et_px.text.toString().toFloat())
            tv_dp.text = "得到的dp值为：$dp"
            Log.d("baibai", "转换后：$dp")
        }

    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

}
