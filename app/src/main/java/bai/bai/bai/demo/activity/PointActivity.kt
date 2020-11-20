package bai.bai.bai.demo.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import bai.bai.bai.demo.R
import bai.bai.bai.demo.utils.ToastUtil
import com.yhao.floatwindow.*


/**
 * 悬浮球  最好放在 Application 里，这里只是样例代码
 * 参考网址：https://www.wanandroid.com/blog/show/2176
 */
class PointActivity : AppCompatActivity() {

    private val TAG = "PointActivity ->"
    private lateinit var mContext: Context
    private lateinit var mPointView: View

    private val mPermissionListener: PermissionListener = object : PermissionListener {
        override fun onSuccess() {}
        override fun onFail() {
            System.exit(0)
        }
    }

    private val mViewStateListener: ViewStateListener = object : ViewStateListener {
        override fun onBackToDesktop() {
        }

        override fun onMoveAnimStart() {
        }

        override fun onMoveAnimEnd() {
        }

        override fun onPositionUpdate(p0: Int, p1: Int) {
        }

        override fun onDismiss() {
        }

        override fun onShow() {
        }

        override fun onHide() {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point)

        mContext = this
//        mPointView = LayoutInflater.from(mContext).inflate(R.layout.view_point, null)
        mPointView = View.inflate(mContext, R.layout.view_point, null);
        mPointView.setOnClickListener {
            ToastUtil.showLong(mContext, "点击了悬浮窗")
        }

//        FloatWindow.with(getApplicationContext())
//                .setView(mPointView)
//                .setX(Screen.width, 0.84f)
//                .setY(Screen.height, 0.8f)
//                .setMoveType(MoveType.slide)
//                .setMoveStyle(500, BounceInterpolator())
//                .setFilter(true, LoginActivity::class.java, ChooseActivity::class.java)
//                .setPermissionListener(mPermissionListener)
//                .build()

        FloatWindow
                .with(getApplicationContext())
                .setView(mPointView)
                .setX(Screen.width, 0.84f)
                .setY(Screen.height, 0.8f)
                .setDesktopShow(true)                        //桌面显示
//                    .setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
                .setPermissionListener(mPermissionListener)  //监听权限申请结果
                .setFilter(false, PointActivity::class.java, ChooseActivity::class.java)//true表示，只有这些activity显示，其他不显示；false则相反
                // MoveType.slide : 可拖动，释放后自动贴边 （默认）
                // MoveType.back : 可拖动，释放后自动回到原位置
                // MoveType.active : 可拖动
                // MoveType.inactive : 不可拖动
                .setMoveType(MoveType.slide)
                // setMoveStyle 方法可设置动画效果，只在 MoveType.slide 或 MoveType.back 模式下设置此项才有意义。默认减速插值器，默认动画时长为 300ms。
                .setMoveStyle(500, BounceInterpolator())
                .build()

    }

}
