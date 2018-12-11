package bai.bai.bai.demo.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import bai.bai.bai.demo.R
import bai.bai.bai.demo.eventbus.MessageEventBean
import bai.bai.bai.demo.retrofit.MyGetTestBean
import kotlinx.android.synthetic.main.activity_event_bus_pull.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class EventBusPullActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus_pull)

        EventBus.getDefault().register(this)
        btn_eventbus_pull.setOnClickListener {
            startActivity(Intent(this@EventBusPullActivity, EventBusPushActivity::class.java))
        }

    }

    @Subscribe
    fun messageEventBus(message: MessageEventBean){
        Log.d("baibai", "我接受的信息是：${message.textStr1} 和${message.textStr2}")
//        Log.d("baibai", "我接受的信息是：${message.address} 和${message.lon}")
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}
