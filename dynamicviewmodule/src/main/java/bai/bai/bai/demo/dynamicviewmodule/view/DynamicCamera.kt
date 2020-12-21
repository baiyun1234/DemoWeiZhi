package bai.bai.bai.demo.dynamicviewmodule.view

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import bai.bai.bai.demo.dynamicviewmodule.R
import bai.bai.bai.demo.dynamicviewmodule.activity.DynamicCameraActivity
import bai.bai.bai.demo.dynamicviewmodule.bean.DynamicBean

/**
 * 拍摄照片
 */
class DynamicCamera(context: Context) : LinearLayout(context) {

    constructor(context: Context, bean: DynamicBean) : this(context) {

        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val contentView = LayoutInflater.from(context).inflate(R.layout.dynamic_camera, null)
        contentView.layoutParams = layoutParams

        val tvLabel = contentView.findViewById<TextView>(R.id.tv_label)
        tvLabel.text = bean.label

        val tvDescription = contentView.findViewById<TextView>(R.id.tv_description)
        tvDescription.text = bean.description

        val ivCamera = contentView.findViewById<View>(R.id.iv_camera)
        ivCamera.setOnClickListener {
            Log.d("DynamicCamera", "点击拍摄|label = ${bean.label}")
            val intent = Intent(context, DynamicCameraActivity::class.java)
            intent.putExtra("path", "bai123")
            context.startActivity(intent)
        }

        this.addView(contentView)
    }


}