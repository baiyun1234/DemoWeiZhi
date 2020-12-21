package bai.bai.bai.demo.activity

import android.content.Context
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import bai.bai.bai.demo.R
import bai.bai.bai.demo.dynamicviewmodule.bean.DynamicBean
import bai.bai.bai.demo.dynamicviewmodule.view.*
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

        //下拉选择框
        val dynamicBeanChoose = DynamicBean()
        dynamicBeanChoose.label = "ID Type"
        dynamicBeanChoose.fieldName = "idType"
        dynamicBeanChoose.fieldValue = "ID Card,Passport"
        Log.d(TAG, "修改前：$dynamicBeanChoose")
        val dynamicChoose = DynamicChoose(mContext, dynamicBeanChoose)
        ll_dynamic_view.addView(dynamicChoose)

        //照相机
        val dynamicBeanCamera = DynamicBean()
        dynamicBeanCamera.label = "Primary document"
        dynamicBeanCamera.fieldName = "primary"
        dynamicBeanCamera.description = "(ID Card/Driving License/Passport)"
        Log.d(TAG, "修改前：$dynamicBeanCamera")
        val dynamicCamera = DynamicCamera(mContext, dynamicBeanCamera)
        ll_dynamic_view.addView(dynamicCamera)

        btn_get.setOnClickListener {
            Log.d(TAG, "Name修改后：$dynamicBeanName")
            Log.d(TAG, "Expired date修改后：$dynamicBeanDate")
            Log.d(TAG, "Currently employed?修改后：$dynamicBeanSwitch")
            Log.d(TAG, "ID Type修改后：$dynamicBeanChoose")
        }

    }

    override fun onActionBarLeft() {
        finish()
    }

    override fun onActionBarRight() {

    }

}
