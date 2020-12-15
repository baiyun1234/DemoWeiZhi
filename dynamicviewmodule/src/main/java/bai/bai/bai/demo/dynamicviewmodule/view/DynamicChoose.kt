package bai.bai.bai.demo.dynamicviewmodule.view

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import bai.bai.bai.demo.dynamicviewmodule.R
import bai.bai.bai.demo.dynamicviewmodule.bean.DynamicBean
import kotlin.collections.ArrayList

/**
 * 单行文本
 */
class DynamicChoose(context: Context) : LinearLayout(context) {

    private lateinit var mBean: DynamicBean

    constructor(context: Context, bean: DynamicBean) : this(context) {
        mBean = bean
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val contentView = LayoutInflater.from(context).inflate(R.layout.dynamic_choose, null)
        contentView.layoutParams = layoutParams

        val tvLabel = contentView.findViewById<TextView>(R.id.tv_label)
        tvLabel.text = bean.label

        val rlChoose = contentView.findViewById<View>(R.id.rl_choose)
        val tvChoose = contentView.findViewById<TextView>(R.id.tv_choose)

        val ivChoose = contentView.findViewById<ImageView>(R.id.iv_choose)
        val data = bean.fieldValue?.split(",") as ArrayList<String>
        Log.d("DynamicChoose", "items = $data")
        ivChoose.setOnClickListener {
            val choosePopupWindow = ChoosePopupWindow()
            choosePopupWindow.showWindow(context, rlChoose, data, tvChoose)
        }

        this.addView(contentView)
    }

    /**
     * 选择下拉框PopupWindow
     */
    inner class ChoosePopupWindow {
        private var mPopupWindow: PopupWindow? = null
        private var mLvChoose: ListView? = null
        private var mAdapter: PopWindowAdapter? = null
        private var mTvChoose: TextView? = null

        /**
         * 下拉菜单（PopupWindow）
         */
        fun showWindow(activity: Context, parent: View, data: ArrayList<String>, textView: TextView) {
            val layoutView: View
            if (mPopupWindow == null) {
                val layoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                layoutView = layoutInflater.inflate(R.layout.dynamic_choose_popup_window, null)
                mTvChoose = textView
                mLvChoose = layoutView.findViewById<View>(R.id.lv_id) as ListView
                mAdapter = PopWindowAdapter(activity, data)
                mLvChoose!!.adapter = mAdapter

                // 创建一个PopuWidow对象,第二个参数是宽，第三个参数是高
                val windowManager = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val wight = windowManager.defaultDisplay.width
                val height = windowManager.defaultDisplay.height / 4
                mPopupWindow = PopupWindow(layoutView, wight, height)
            }

            mAdapter?.notifyDataSetChanged()

            mPopupWindow!!.isFocusable = true// 使其聚集
            mPopupWindow!!.isOutsideTouchable = true// 设置允许在外点击消失
            mPopupWindow!!.setBackgroundDrawable(BitmapDrawable())// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
            mPopupWindow!!.showAsDropDown(parent)//在某个布局正下面
        }

        fun dismiss() {
            mPopupWindow?.dismiss()
            mPopupWindow = null
        }

        private inner class PopWindowAdapter(private val mContext: Context, data: ArrayList<String>) : BaseAdapter() {

            private val mItemData: ArrayList<String>

            init {
                this.mItemData = getItemData(data)
            }

            private fun getItemData(data: ArrayList<String>?): ArrayList<String> {
                var data1 = data
                if (data1 == null) {
                    data1 = arrayListOf()
                }
                return data1
            }

            override fun getCount(): Int {
                return mItemData.size
            }

            override fun getItem(position: Int): String {
                return mItemData[position]
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var convertView1 = convertView
                val holder: ViewHolder
                if (convertView1 == null) {
                    holder = ViewHolder()
                    convertView1 = LayoutInflater.from(mContext).inflate(R.layout.dynamic_choose_item_popup_window, null)
                    holder.tvId = convertView1!!.findViewById<View>(R.id.tv_id) as TextView
                    convertView1.tag = holder
                } else {
                    holder = convertView1.tag as ViewHolder
                }
                val currentData = mItemData[position]
                holder.tvId!!.text = currentData
                holder.tvId!!.setOnClickListener(View.OnClickListener {
                    mTvChoose!!.text = currentData
                    mBean.fieldInput = currentData
                    if (mPopupWindow != null) {
                        mPopupWindow!!.dismiss()
                    }
                })
                return convertView1
            }

            private inner class ViewHolder {
                var tvId: TextView? = null
            }

        }

    }

}