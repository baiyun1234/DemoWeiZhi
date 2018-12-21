package bai.bai.bai.demo.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import bai.bai.bai.demo.R
import bai.bai.bai.demo.service.MyService
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        btn_start_service.setOnClickListener {
            val intent = Intent(this@ServiceActivity, MyService::class.java)
            startService(intent)
        }

        btn_stop_service.setOnClickListener {
            val intent = Intent(this@ServiceActivity, MyService::class.java)
            stopService(intent)
        }

    }
}
