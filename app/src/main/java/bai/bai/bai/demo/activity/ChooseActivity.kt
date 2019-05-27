package bai.bai.bai.demo.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import bai.bai.bai.demo.R
import bai.bai.bai.demo.systembar.SystemBarTintManager
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : Activity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        setStatusBar()
        initData()
        initListener()

    }

    private fun setStatusBar() {
        // 创建状态栏的管理实例
        val tintManager = SystemBarTintManager(this)
        // 激活状态栏设置
        tintManager.isStatusBarTintEnabled = true
        // 设置一个颜色给系统栏
        tintManager.setStatusBarTintColor(resources.getColor(R.color.base_action_bar_bg))
    }

    private fun initData() {

    }

    private fun initListener() {
        btn_choose_snap_pay.setOnClickListener(this)
        btn_choose_location.setOnClickListener(this)
        btn_choose_ip.setOnClickListener(this)
        btn_choose_java.setOnClickListener(this)
        btn_choose_view.setOnClickListener(this)
        btn_choose_assets_file.setOnClickListener(this)
        btn_choose_retrofit.setOnClickListener(this)
        btn_choose_date.setOnClickListener(this)
        btn_choose_rxjava.setOnClickListener(this)
        btn_choose_time_zone.setOnClickListener(this)
        btn_choose_eventbus.setOnClickListener(this)
        btn_choose_service.setOnClickListener(this)
        btn_choose_fragment.setOnClickListener(this)
        btn_choose_time_data_picker.setOnClickListener(this)
        btn_choose_calendar.setOnClickListener(this)
        btn_choose_screen_change.setOnClickListener(this)
        btn_choose_count_down.setOnClickListener(this)
        btn_choose_web_view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.btn_choose_snap_pay -> {
                startActivity(Intent(this, MainActivity::class.java))
            }

            R.id.btn_choose_location -> {
                startActivity(Intent(this, LocationActivity::class.java))
            }

            R.id.btn_choose_ip -> {
                startActivity(Intent(this, IpActivity::class.java))
            }

            R.id.btn_choose_java -> {
                startActivity(Intent(this, JavaViewTestActivity::class.java))
            }

            R.id.btn_choose_view -> {
                startActivity(Intent(this, ViewTestActivity::class.java))
            }

            R.id.btn_choose_assets_file -> {
                startActivity(Intent(this, AssetsFileActivity::class.java))
            }

            R.id.btn_choose_retrofit -> {
                startActivity(Intent(this, RetrofitActivity::class.java))
            }

            R.id.btn_choose_date -> {
                startActivity(Intent(this, DateActivity::class.java))
            }

            R.id.btn_choose_rxjava -> {
                startActivity(Intent(this, RxjavaActivity::class.java))
            }

            R.id.btn_choose_time_zone -> {
                startActivity(Intent(this, TimeZoneActivity::class.java))
            }

            R.id.btn_choose_eventbus -> {
                startActivity(Intent(this, EventBusPullActivity::class.java))
            }

            R.id.btn_choose_service -> {
                startActivity(Intent(this, ServiceActivity::class.java))
            }

            R.id.btn_choose_fragment -> {
                startActivity(Intent(this, MyFragmentActivity::class.java))
            }

            R.id.btn_choose_time_data_picker -> {
                startActivity(Intent(this, TimeDatePickerActivity::class.java))
            }

            R.id.btn_choose_calendar -> {
                startActivity(Intent(this, CalendarActivity::class.java))
            }

            R.id.btn_choose_screen_change -> {
                startActivity(Intent(this, ScreenChangeActivity::class.java))
            }

            R.id.btn_choose_count_down -> {
                startActivity(Intent(this, CountDownActivity::class.java))
            }

            R.id.btn_choose_web_view -> {
                startActivity(Intent(this, WebViewActivity::class.java))
            }

        }
    }

}
