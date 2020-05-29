package bai.bai.bai.demo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import bai.bai.bai.demo.R
import bai.bai.bai.demo.utils.DeviceUtil2
import kotlinx.android.synthetic.main.activity_get_current_activity.*

class GetCurrentActivityActivity : AppCompatActivity() {

    private val TAG = "GetCurrentActivity -> "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_current_activity)

        btn_get_current_activity.setOnClickListener {
            val currentActivity = DeviceUtil2.getCurrentActivity()
            Log.d(TAG, "当前activity：${currentActivity.localClassName}")
        }

    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        val currentActivity = DeviceUtil2.getCurrentActivity()
        Log.d(TAG, "onDestroy|当前activity：${currentActivity.localClassName}")
        super.onDestroy()
    }
}
