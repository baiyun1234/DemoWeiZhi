package bai.bai.bai.demo.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder

class MyBroadcast: BroadcastReceiver(){

    //1、变量
    private val TAG = "MyBroadcast"

    //2、重写BroadcastReceiver的方法
    //抽象方法
    override fun onReceive(context: Context?, intent: Intent?) {

    }

    //非抽象方法
    override fun peekService(myContext: Context?, service: Intent?): IBinder {
        return super.peekService(myContext, service)
    }

    //3、其他私有方法
    private fun todoSomething(){

    }

}