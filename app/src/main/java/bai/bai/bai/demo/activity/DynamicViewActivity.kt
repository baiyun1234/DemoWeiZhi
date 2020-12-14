package bai.bai.bai.demo.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bai.bai.bai.demo.R
import bai.bai.bai.demo.dynamicviewmodule.view.DynamicTextView
import bai.bai.bai.demo.dynamicviewmodule.bean.DynamicBean
import bai.bai.bai.demo.dynamicviewmodule.view.DynamicDate
import bai.bai.bai.demo.dynamicviewmodule.view.DynamicSwitch
import bai.bai.bai.demo.view.BaseActionBar
import kotlinx.android.synthetic.main.activity_dynamic_view.*

/**
 * 动态生成控件
 */
class DynamicViewActivity : AppCompatActivity(), BaseActionBar.ActionBarClickListener {

    private val TAG = "DynamicViewActivity"
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_view)

        initData()
        intiView()
    }

    private fun initData() {
        mContext = this
    }

    private fun intiView() {
        action_bar.setActionBarClickListener(this)

        //单行文本
        val dynamicBeanName = DynamicBean()
        dynamicBeanName.label = "Name"
        dynamicBeanName.fieldName = "name"
        Log.d(TAG, "修改前：$dynamicBeanName")
        val dynamicTextView = DynamicTextView(mContext, dynamicBeanName)
        ll_dynamic_view.addView(dynamicTextView)

        //日期选择框
        val dynamicBeanDate = DynamicBean()
        dynamicBeanDate.label = "Expired date"
        dynamicBeanDate.fieldName = "date"
        Log.d(TAG, "修改前：$dynamicBeanDate")
        val dynamicDate = DynamicDate(mContext, dynamicBeanDate)
        ll_dynamic_view.addView(dynamicDate)

        //开关
        val dynamicBeanSwitch = DynamicBean()
        dynamicBeanSwitch.label = "Currently employed?"
        dynamicBeanSwitch.fieldName = "isEmployed"
        Log.d(TAG, "修改前：$dynamicBeanSwitch")
        val dynamicSwitch = DynamicSwitch(mContext, dynamicBeanSwitch)
        ll_dynamic_view.addView(dynamicSwitch)


        btn_get.setOnClickListener {
            Log.d(TAG, "Name修改后：$dynamicBeanName")
            Log.d(TAG, "Expired date修改后：$dynamicBeanDate")
            Log.d(TAG, "Currently employed?修改后：$dynamicBeanSwitch")
        }

    }

    override fun onActionBarLeft() {
        finish()
    }

    override fun onActionBarRight() {

    }

}
