package bai.bai.bai.demo.dynamicviewmodule.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import bai.bai.bai.demo.dynamicviewmodule.R
import bai.bai.bai.demo.dynamicviewmodule.bean.DynamicBean

/**
 * 开关
 */
class DynamicSwitch(context: Context) : LinearLayout(context) {

    constructor(context: Context, bean: DynamicBean) : this(context) {

        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val contentView = LayoutInflater.from(context).inflate(R.layout.dynamic_switch, null)
        contentView.layoutParams = layoutParams

        val tvLabel = contentView.findViewById<TextView>(R.id.tv_label)
        tvLabel.text = bean.label

        val switchInput = contentView.findViewById<Switch>(R.id.switch_input)
        switchInput.setOnCheckedChangeListener { buttonView, isChecked ->
            bean.fieldInput = isChecked.toString()
        }

        this.addView(contentView)
    }


}