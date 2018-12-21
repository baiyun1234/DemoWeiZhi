package bai.bai.bai.demo.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log


class MyService : Service(), MyInterface {

    //1、变量
    private val TAG = "MyService"

    //2、重写Service生命周期
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    //3、实现接口的方法
    override fun interfaceMethed1() {
    }

    override fun interfaceMethed2() {
    }

    override fun interfaceMethed3() {
    }

    //4、初始化的相关方法
    fun init() {

    }

    //5、其实私有方法

}
