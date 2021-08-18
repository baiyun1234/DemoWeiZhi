package bai.bai.bai.demo.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import bai.bai.bai.demo.R
import bai.bai.bai.demo.systembar.SystemBarTintManager
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : Activity(), View.OnClickListener {

    private val TAG = "ChooseActivity -> "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        Log.d(TAG, "---onCreate()---")
        setStatusBar()
        initData()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "---onResume()---")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "---onStop()---")
    }

    override fun onDestroy() {
        Log.d(TAG, "---onDestroy()---")
        super.onDestroy()
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

            R.id.btn_choose_px_to_dp -> {
                startActivity(Intent(this, PxToDpActivity::class.java))
            }

            R.id.btn_choose_screen_shot -> {
                startActivity(Intent(this, ScreenShotActivity::class.java))
            }

            R.id.btn_choose_action_bar -> {
                startActivity(Intent(this, ActionBarActivity::class.java))
            }

            R.id.btn_choose_get_current_activity -> {
                startActivity(Intent(this, GetCurrentActivityActivity::class.java))
            }

            R.id.btn_choose_point -> {
                startActivity(Intent(this, PointActivity::class.java))
            }

            R.id.btn_choose_shade -> {
                startActivity(Intent(this, ShadeButtonActivity::class.java))
            }

            R.id.btn_choose_camera -> {
                startActivity(Intent(this, CameraActivity::class.java))
            }

            R.id.btn_choose_dynamic_view -> {
                startActivity(Intent(this, DynamicViewActivity::class.java))
            }

            R.id.btn_choose_nfc -> {
                startActivity(Intent(this, NfcReadActivity::class.java))
            }

        }
    }

}
