package bai.bai.bai.demo.dynamicviewmodule.view

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import bai.bai.bai.demo.baseModule.util.DateUtil
import bai.bai.bai.demo.dynamicviewmodule.R
import bai.bai.bai.demo.dynamicviewmodule.bean.DynamicBean
import java.text.SimpleDateFormat

/**
 * 日期选择器
 */
class DynamicDate(context: Context) : LinearLayout(context) {

    private val format = SimpleDateFormat("yyyy-MM-dd")
    private var selectDateAndTime = DateUtil.StringToDate(DateUtil.getCurDateStr(), "yyyy-MM-dd")

    constructor(context: Context, dynamicBean: DynamicBean) : this(context) {
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val contentView = LayoutInflater.from(context).inflate(R.layout.dynamic_date, null)
        contentView.layoutParams = layoutParams

        val tvLabel = contentView.findViewById<TextView>(R.id.tv_label)
        tvLabel.text = dynamicBean.label

        val tvChoose = contentView.findViewById<TextView>(R.id.tv_choose)

        val ivChoose = contentView.findViewById<ImageView>(R.id.iv_choose)
        ivChoose.setOnClickListener {
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                selectDateAndTime = DateUtil.setDate(selectDateAndTime, year, month, day)
                val dateStr = format.format(selectDateAndTime)
                tvChoose.text = dateStr
                dynamicBean.fieldInput = dateStr
            }, selectDateAndTime.year + 1900, selectDateAndTime.month, selectDateAndTime.date).show()//year是从1900后开始的，所以要加1
        }

        this.addView(contentView)
    }

}