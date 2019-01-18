package bai.bai.bai.demo.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import bai.bai.bai.demo.R
import bai.bai.bai.demo.view.calendar.*
import bai.bai.bai.demo.view.calendar.Calendar
import bai.bai.bai.demo.view.calendar.custom.circle.CircleMonthView
import bai.bai.bai.demo.view.calendar.custom.circle.CircleWeekView
import kotlinx.android.synthetic.main.activity_calendar.*
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SetTextI18n")
class CalendarActivity : AppCompatActivity(), CalendarView.OnCalendarSelectListener,
        CalendarView.OnCalendarLongClickListener,
        CalendarView.OnMonthChangeListener,
        CalendarView.OnYearChangeListener,
        CalendarView.OnWeekChangeListener,
        CalendarView.OnViewChangeListener,
        CalendarView.OnCalendarInterceptListener,
        DialogInterface.OnClickListener,
        View.OnClickListener {

    private var mYear: Int = 0

    private var mMoreDialog: AlertDialog? = null
    private var mFuncDialog: AlertDialog? = null

    private var mListener: DialogInterface.OnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        initData()
        initView()
        initListener()

    }

    private fun initData() {

        val map = HashMap<String, Calendar>()
        for (y in 1997..2081) {
            for (m in 1..12) {
                map[getSchemeCalendar(y, m, 1, -0xbf24db, "假").toString()] = getSchemeCalendar(y, m, 1, -0xbf24db, "假")
            }
        }

        //28560 数据量增长不会影响UI响应速度，请使用这个API替换
        calendarView.setSchemeDate(map)

    }

    private fun initView() {

        mListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                0 -> calendarLayout.expand()
                1 -> calendarLayout.shrink()
                2 -> calendarView.scrollToPre(true)
                3 -> calendarView.scrollToNext(true)
                4 -> calendarView.scrollToCurrent(true)
                5 -> calendarView.setRange(2016, 7, 1, 2016, 9, 28)
                6 -> {
                    Log.e("scheme", "  " + calendarView.selectedCalendar.scheme + "  --  "
                            + calendarView.selectedCalendar.isCurrentDay)
                    val weekCalendars = calendarView.currentWeekCalendars
                    for (calendar in weekCalendars) {
                        Log.e("onWeekChange", calendar.toString() + "  --  " + calendar.scheme)
                    }
                    AlertDialog.Builder(this@CalendarActivity)
                            .setMessage(String.format("Calendar Range: \n%s —— %s",
                                    calendarView.minRangeCalendar,
                                    calendarView.maxRangeCalendar))
                            .show()
                }
            }
        }

        tv_year.text = calendarView.curYear.toString()
        mYear = calendarView.curYear
        tv_month_day.text = "${calendarView.curMonth} 月 ${calendarView.curDay} 日"
        tv_lunar.text = "今日"
        tv_current_day.text = "${calendarView.curDay}"
    }

    private fun initListener() {
        tv_month_day.setOnClickListener(this)
        iv_more.setOnClickListener(this)
        iv_func.setOnClickListener(this)

        calendarView.setOnYearChangeListener(this)
        calendarView.setOnCalendarSelectListener(this)
        calendarView.setOnMonthChangeListener(this)
        calendarView.setOnCalendarLongClickListener(this, true)
        calendarView.setOnWeekChangeListener(this)

        //设置日期拦截事件，仅适用单选模式，当前无效
        calendarView.setOnCalendarInterceptListener(this)
        calendarView.setOnViewChangeListener(this)

        btn_pre.setOnClickListener(this)
        btn_next.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.tv_month_day -> {
                if (!calendarLayout.isExpand) {
                    calendarLayout.expand(0)
                    return
                }
                calendarView.showYearSelectLayout(mYear)
                tv_lunar.visibility = View.GONE
                tv_year.visibility = View.GONE
                tv_month_day.text = mYear.toString()
            }

            R.id.iv_more -> {
                if (mMoreDialog == null) {
                    mMoreDialog = AlertDialog.Builder(this@CalendarActivity)
                            .setTitle("我是more的标题")
                            .setItems(R.array.list_dialog_items, this@CalendarActivity)
                            .create()
                }
                mMoreDialog?.show()
            }

            R.id.iv_func -> {
                if (mFuncDialog == null) {
                    mFuncDialog = AlertDialog.Builder(this@CalendarActivity)
                            .setTitle("我是什么标题")
                            .setItems(R.array.func_dialog_items, mListener)
                            .create()
                }
                mFuncDialog?.show()
            }

            R.id.btn_pre -> {
                calendarView.scrollToPreviousDay(true)
            }

            R.id.btn_next -> {
                calendarView.scrollToNextDay(true)
            }

        }

    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        when (which) {
            0 -> calendarView.setWeekStarWithSun()
            1 -> calendarView.setWeekStarWithMon()
            2 -> calendarView.setWeekStarWithSat()
            3 -> if (calendarView.isSingleSelectMode) {
                calendarView.setSelectDefaultMode()
            } else {
                calendarView.setSelectSingleMode()
            }
            4 -> {
//                calendarView.setWeekView(MeizuWeekView::class.java)
//                calendarView.setMonthView(MeiZuMonthView::class.java)

                calendarView.setWeekView(CircleWeekView::class.java)
                calendarView.setMonthView(CircleMonthView::class.java)
//                calendarView.setWeekBar(CircleWeekBar::class.java)
            }
            5 -> calendarView.setAllMode()
            6 -> calendarView.setOnlyCurrentMode()
            7 -> calendarView.setFixMode()
        }
    }

    private fun getSchemeCalendar(year: Int, month: Int, day: Int, color: Int, text: String): Calendar {
        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
        calendar.schemeColor = color//如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        return calendar
    }

    override fun onCalendarOutOfRange(calendar: Calendar) {
        Toast.makeText(this, String.format("%s : OutOfRange", calendar), Toast.LENGTH_SHORT).show()
    }

    override fun onCalendarSelect(calendar: Calendar, isClick: Boolean) {
        tv_lunar.visibility = View.VISIBLE
        tv_year.visibility = View.VISIBLE
        tv_month_day.text = "${calendar.month} 月 ${calendar.day} 日"
        tv_year.text = "${calendar.year}"
        tv_lunar.text = calendar.lunar
        mYear = calendar.year
        if (isClick) {
            Toast.makeText(this, getCalendarText(calendar), Toast.LENGTH_SHORT).show()
        }
        Log.e("onDateSelected", ("  -- " + calendar.year +
                "  --  " + calendar.month +
                "  -- " + calendar.day +
                "  --  " + isClick + "  --   " + calendar.scheme))
        Log.e("onDateSelected", ("  " + calendarView.selectedCalendar.scheme +
                "  --  " + calendarView.selectedCalendar.isCurrentDay))
    }

    override fun onCalendarLongClickOutOfRange(calendar: Calendar) {
        Toast.makeText(this, String.format("%s : LongClickOutOfRange", calendar), Toast.LENGTH_SHORT).show()
    }

    override fun onCalendarLongClick(calendar: Calendar) {
        Toast.makeText(this, "长按不选择日期\n" + getCalendarText(calendar), Toast.LENGTH_SHORT).show()
    }

    private fun getCalendarText(calendar: Calendar): String {
        return String.format("新历%s \n 农历%s \n 公历节日：%s \n 农历节日：%s \n 节气：%s \n 是否闰月：%s",
                "${calendar.month} 月 ${calendar.day} 日"
                , "${calendar.lunarCalendar.month} 月 ${calendar.lunarCalendar.day} 日"
                ,
                if (TextUtils.isEmpty(calendar.gregorianFestival)) "无" else calendar.gregorianFestival,
                if (TextUtils.isEmpty(calendar.traditionFestival)) "无" else calendar.traditionFestival,
                if (TextUtils.isEmpty(calendar.solarTerm)) "无" else calendar.solarTerm,
                if (calendar.leapMonth == 0) "否" else String.format("闰%s月", calendar.leapMonth))
    }

    override fun onMonthChange(year: Int, month: Int) {
        //Log.e("onMonthChange", "  -- " + year + "  --  " + month);
        val calendar = calendarView.selectedCalendar
        tv_lunar.visibility = View.VISIBLE
        tv_year.visibility = View.VISIBLE
        tv_month_day.text = "${calendar.month} 月 ${calendar.day} 日"
        tv_year.text = "${calendar.year}"
        tv_lunar.text = calendar.lunar
        mYear = calendar.year
    }

    override fun onViewChange(isMonthView: Boolean) {
        Log.e("onViewChange", "  ---  " + (if (isMonthView) "月视图" else "周视图"))
    }

    override fun onWeekChange(weekCalendars: List<Calendar>) {
        for (calendar in weekCalendars) {
            Log.e("onWeekChange", calendar.toString())
        }
    }

    /**
     * 屏蔽某些不可点击的日期，可根据自己的业务自行修改
     *
     * @param calendar calendar
     * @return 是否屏蔽某些不可点击的日期，MonthView和WeekView有类似的API可调用
     */
    override fun onCalendarIntercept(calendar: Calendar): Boolean {
        Log.e("onCalendarIntercept", calendar.toString())
        val day = calendar.day
        return day == 1 || day == 3 || day == 6 || day == 11 || day == 12 || day == 15 || day == 20 || day == 26
    }

    override fun onCalendarInterceptClick(calendar: Calendar, isClick: Boolean) {
        Toast.makeText(this, calendar.toString() + "拦截不可点击", Toast.LENGTH_SHORT).show()
    }

    override fun onYearChange(year: Int) {
        tv_month_day.text = (year).toString()
    }

}
