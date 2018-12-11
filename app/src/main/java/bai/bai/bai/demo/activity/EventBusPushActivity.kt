package bai.bai.bai.demo.activity

import android.app.Activity
import android.os.Bundle
import bai.bai.bai.demo.R
import bai.bai.bai.demo.eventbus.MessageEventBean
import kotlinx.android.synthetic.main.activity_event_bus_push.*
import org.greenrobot.eventbus.EventBus

/**
 * EventBus接受事件的一方，要注册和解注册
 */
class EventBusPushActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus_push)


        btn_eventbus_push.setOnClickListener {
            val message = MessageEventBean()
            message.textStr1 = "baibai"
            message.textStr2 = "yunyun"
            EventBus.getDefault().post(message)


        }
    }

}
