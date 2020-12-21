package bai.bai.bai.demo.dynamicviewmodule.activity

//import android.support.v7.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import bai.bai.bai.demo.dynamicviewmodule.R
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.activity_dynamic_camera.*
import java.io.File

/**
 * 对于能够感知生命周期的 Activity，请使用 FragmentActivity 或 AppCompatActivity。
 */
class DynamicCameraActivity : AppCompatActivity() {

    private val TAG = "DynamicCameraActivity"
    private lateinit var mContext: Context
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var mPreview: Preview
    private lateinit var mCapture: ImageCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_camera)

        initData()
        initView()
    }

    private fun initData() {
        mContext = this
        //可返回当前可以绑定生命周期的 ProcessCameraProvider
        cameraProviderFuture = ProcessCameraProvider.getInstance(mContext)
        cameraProviderFuture.addListener(Runnable {
            bindPreview()
        }, ContextCompat.getMainExecutor(mContext))

    }

    private fun initView() {
        btn_camera.setOnClickListener {
            capture()
        }
    }

    /**
     * 选择相机并绑定生命周期和用例
     */
    private fun bindPreview() {

        //将相机的生命周期和activity的生命周期绑定，camerax 会自己释放
        val cameraProvider = cameraProviderFuture.get()

        //预览
        mPreview = Preview.Builder()
                .build()

        //拍摄
        mCapture = ImageCapture.Builder()
//                .setTargetRotation(preview_view.display?.rotation!!)
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)//如需缩短照片拍摄的延迟时间，请将 ImageCapture.CaptureMode 设置为 CAPTURE_MODE_MINIMIZE_LATENCY。如需优化照片质量，请将其设置为 CAPTURE_MODE_MAXIMIZE_QUALITY。
                .build()

        val cameraSelector: CameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

//        //预览之前先解绑
        cameraProvider.unbindAll()

//        mPreview.setSurfaceProvider(preview_view.surfaceProvider)//1.0.0-alpha16 版本的用这一行，但是目前报错
        mPreview.setSurfaceProvider(preview_view.createSurfaceProvider())//1.0.0-alpha11 到 15 的时候使用这一行

        var camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, mPreview, mCapture)
//        mPreview.setSurfaceProvider(preview_view.createSurfaceProvider(camera.cameraInfo))//1.0.0-alpha10 的时候使用这一行


    }

    /**
     * 拍摄
     */
    private fun capture() {
        Log.d(TAG, "capture")
        val files = File(Environment.getExternalStorageDirectory().absolutePath + "/baibai")
        Log.d(TAG, "capture|files = " + files.absolutePath)
        if (!files.exists()) {
            Log.d(TAG, "capture|文件夹不存在，立即新建")
            files.mkdirs()
        }
        val str = System.currentTimeMillis().toString() + ""
        val file = File(files.absoluteFile.toString() + "/" + str + ".png")
//        val file = File(files.absoluteFile.toString() + "/yan.jpg")

        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()
        val cameraExecutor = ContextCompat.getMainExecutor(mContext)

        mCapture.takePicture(outputFileOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {
            override fun onError(error: ImageCaptureException) {
                error.printStackTrace()
                Log.d(TAG, "capture|onError -> useCaseError = " + error.message)
                Log.d(TAG, "capture|onError -> useCaseError = " + error.imageCaptureError)
            }

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Log.d(TAG, "capture|onImageSaved -> file" + file.absolutePath)
                //发广播刷新图库，不然只能在文件管理器找到，而图库不会自动更新
                mContext.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.absolutePath)))

            }
        })
    }

}
