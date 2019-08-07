package bai.bai.bai.demo.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import bai.bai.bai.demo.R
import kotlinx.android.synthetic.main.activity_screen_shot.*
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Log
import bai.bai.bai.demo.utils.ToastUtil
import java.io.File
import java.io.FileOutputStream


class ScreenShotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_shot)

        btn_screen.setOnClickListener {
            screenShot()
        }

    }

    private fun screenShot() {
        val dView = window.decorView
        dView.isDrawingCacheEnabled = true
        dView.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(dView.drawingCache)
        if (bitmap != null) {
            try {
                // 获取内置SD卡路径
                val sdCardPath = Environment.getExternalStorageDirectory().path
                // 图片文件路径
                val filePath = sdCardPath + File.separator + System.currentTimeMillis() + "screenshot.png"
                val file = File(filePath)
                val os = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
                os.flush()
                os.close()
                //发广播刷新图库，不然只能在文件管理器找到，而图库不会自动更新
                this.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.absolutePath)))
                Log.d("baibai", "存储完成")
                ToastUtil.showLong(this, "存储完成")
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}
