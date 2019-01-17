package bai.bai.bai.demo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import bai.bai.bai.demo.R
import bai.bai.bai.demo.view.timepicker.CustomDatePicker
import kotlinx.android.synthetic.main.activity_time_date_picker.*
import java.text.SimpleDateFormat
import java.util.*

class TimeDatePickerActivity : AppCompatActivity(), View.OnClickListener {

    private var datePicker: CustomDatePicker? = null
    private var timePicker: CustomDatePicker? = null
    private var time: String? = null
    private var date: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_date_picker)

        selectTime.setOnClickListener(this)
        selectDate.setOnClickListener(this)
        initPicker()

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.selectDate ->
                // 日期格式为yyyy-MM-dd
                datePicker?.show(date)

            R.id.selectTime ->
                // 日期格式为yyyy-MM-dd HH:mm
                timePicker?.show(time)
        }
    }

    private fun initPicker() {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
        time = sdf.format(Date())
        date = time?.split(" ".toRegex())?.dropLastWhile({ it.isEmpty() })?.toTypedArray()!![0]
        //设置当前显示的日期
        currentDate?.text = date
        //设置当前显示的时间
        currentTime?.text = time

        /**
         * 设置年月日
         */
        datePicker = CustomDatePicker(this, "请选择日期", CustomDatePicker.ResultHandler { time ->
            currentDate?.text = time.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        }, "2007-01-01 00:00", time)
        datePicker?.showSpecificTime(false) //显示时和分
        datePicker?.setIsLoop(false)
        datePicker?.setDayIsLoop(true)
        datePicker?.setMonIsLoop(true)

        timePicker = CustomDatePicker(this, "请选择时间", CustomDatePicker.ResultHandler { time ->
            currentTime?.text = time
        }, "2007-01-01 00:00", "2027-12-31 23:59")//"2027-12-31 23:59"
        timePicker?.showSpecificTime(true)
        timePicker?.setIsLoop(true)
    }
}
