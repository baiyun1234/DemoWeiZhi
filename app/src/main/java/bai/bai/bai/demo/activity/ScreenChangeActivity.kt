package bai.bai.bai.demo.activity

import android.app.Activity
import android.os.Build
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
//import android.support.annotation.RequiresApi
import android.util.Log
import androidx.annotation.RequiresApi
import bai.bai.bai.demo.R

class ScreenChangeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_change)
        Log.d("baibai", "onCreate")
        if (savedInstanceState != null)
            Log.d("baibai", "onCreate : 姓名：${savedInstanceState.getString("name_name")}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("baibai", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("baibai", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("baibai", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("baibai", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("baibai", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("baibai", "onDestroy")
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d("baibai", "onSaveInstanceState")
        outPersistentState?.putString("name_name", "bai")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null)
            Log.d("baibai", "onRestoreInstanceState : 姓名：${savedInstanceState.getString("name_name")}")
    }

}
