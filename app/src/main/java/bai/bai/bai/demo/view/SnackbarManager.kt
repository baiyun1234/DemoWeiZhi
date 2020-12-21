package bai.bai.bai.demo.view

//import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * Created by daigaokai on 2018/4/26.
 */
object SnackbarManager {


    private var snackbar: Snackbar? = null

    fun snackbar(view: View, content: String) {
        startSnackbar(view, content, Toast.LENGTH_SHORT)
    }

    fun snackbarLong(view: View, content: String) {
        startSnackbar(view, content, Toast.LENGTH_LONG)
    }

    private fun startSnackbar(view: View, content: String, duration: Int) {
        if (snackbar == null) {
            snackbar = Snackbar.make(view, content, duration)
        }
        // 注意：小米手机如果直接Toast.makeText(ctx)，那么显示的内容包为：应用名称:content，再执行一遍setText()就不会显示应用名称了
        snackbar?.setText(content)
        snackbar?.duration = duration
        snackbar?.setAction("右侧按钮", View.OnClickListener {
            snackbar?.dismiss()
        })
        snackbar?.show()
    }



}