package bai.bai.bai.demo.dynamicviewmodule.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import bai.bai.bai.demo.dynamicviewmodule.R
import bai.bai.bai.demo.dynamicviewmodule.bean.DynamicBean

/**
 * 单行文本
 */
class DynamicTextView(context: Context) : LinearLayout(context) {

    private lateinit var mTvLabel: TextView
    private lateinit var mEtValue: EditText

    constructor(context: Context, bean: DynamicBean) : this(context) {

        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val contentView = LayoutInflater.from(context).inflate(R.layout.dynamic_text_view, null)
        contentView.layoutParams = layoutParams

        mTvLabel = contentView.findViewById(R.id.tv_label)
        mTvLabel.text = bean.label

        mEtValue = contentView.findViewById(R.id.et_input)
        mEtValue.hint = bean.label
        mEtValue.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bean.fieldInput = s?.toString()
            }

        })

        this.addView(contentView)
    }


}