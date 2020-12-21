package bai.bai.bai.demo.activity

import android.app.Activity
import android.os.Bundle
//import android.support.v4.view.WindowCompat
import android.view.Window
import bai.bai.bai.demo.R
import bai.bai.bai.demo.view.BaseActionBar
import kotlinx.android.synthetic.main.activity_action_bar.*

class ActionBarActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //复制粘贴布局设置成悬浮式
        requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);//如果是继承Activity类则用requestWindowFeature方法
//        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_MODE_OVERLAY)//如果是继承ActionBar或AppCompatActivity则使用supportRequestWindowFeature
        setContentView(R.layout.activity_action_bar)

        action_bar.setActionBarClickListener(object : BaseActionBar.ActionBarClickListener {
            override fun onActionBarRight() {
            }

            override fun onActionBarLeft() {
                finish()
            }
        })
    }
}
