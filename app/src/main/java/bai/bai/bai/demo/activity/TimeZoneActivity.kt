package bai.bai.bai.demo.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import bai.bai.bai.demo.R
import bai.bai.bai.demo.timezone.DateUtilTZ
import kotlinx.android.synthetic.main.activity_time_zone.*
import java.text.DateFormat
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

class TimeZoneActivity : Activity(), View.OnClickListener {

    private val TAG = "baibai"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_zone)

        btn_show_time.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_show_time -> {
                /**Nov 27, 2018 20:55:34*/
                val str = DateUtilTZ.getDateTimeString("America/Toronto")
//                Log.d(TAG, "DateFormat.DEFAULT :str = $str")

                /**Tuesday, November 27, 2018*/
                val str1 = DateUtilTZ.getDateString(DateFormat.FULL, "America/Toronto")
//                Log.d(TAG, "DateFormat.FULL : str1 = $str1")

                /**November 27, 2018*/
                val str2 = DateUtilTZ.getDateString(DateFormat.LONG, "America/Toronto")
//                Log.d(TAG, "DateFormat.LONG : str2 = $str2")

                /**Nov 27, 2018*/
                val str3 = DateUtilTZ.getDateString(DateFormat.MEDIUM, "America/Toronto")
//                Log.d(TAG, "DateFormat.MEDIUM : str3 = $str3")

                /**2018-11-27*/
                val str4 = DateUtilTZ.getDateString(DateFormat.SHORT, "America/Toronto")
//                Log.d(TAG, "DateFormat.SHORT : str4 = $str4")

                Log.d(TAG, "===============================================")

                /**Tue Nov 27 00:00:00 GMT+08:00 2018*/
//                val date1 = DateUtilTZ.parseDateStringToDate(str)
//                Log.d(TAG, "date1 = $date1")

                /**Tue Nov 27 13:00:00 GMT+08:00 2018*/
                val date2 = DateUtilTZ.parseDateStringToDate(str, "America/Toronto")
//                Log.d(TAG, "date2 = $date2")

                /**2018-11-27 20:55:34*/
                val aaa = DateUtilTZ.getDateTimeString(null, DateFormat.SHORT, DateFormat.DEFAULT, "America/Toronto")
//                Log.d(TAG, "aaa = $aaa")

                val aa = "2018-11-28 11:59:55"
                aa2bb(aa)
            }
        }
    }

    /**
     * 某个时间转化成指定时区的时间
     */
    private fun aa2bb(aa: String){
        Log.d(TAG, "输入的时间：$aa")
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = df.parse(aa)
        val cal = Calendar.getInstance()
        cal.time = date
        val timestamp = cal.timeInMillis
        Log.d(TAG, "时间戳：$timestamp")
        df.timeZone = TimeZone.getTimeZone("America/Toronto")
        val bb = df.format(Date(timestamp))
        Log.d(TAG, "输出的时间：$bb")
    }

}
