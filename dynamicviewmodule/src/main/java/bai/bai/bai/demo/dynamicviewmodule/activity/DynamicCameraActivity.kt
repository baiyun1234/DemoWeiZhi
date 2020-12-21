package bai.bai.bai.demo.dynamicviewmodule.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
//import androidx.core.content.ContextCompat
//import androidx.camera.camera2.Camera2Config
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.CameraXConfig
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.lifecycle.LifecycleOwner
import bai.bai.bai.demo.dynamicviewmodule.R
//import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.activity_dynamic_camera.*

/**
 * 对于能够感知生命周期的 Activity，请使用 FragmentActivity 或 AppCompatActivity。
 */
class DynamicCameraActivity : AppCompatActivity() {

    private lateinit var mContext: Context
//    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_camera)

        initData()
        initView()
    }

    private fun initData() {
        mContext = this
//        cameraProviderFuture = ProcessCameraProvider.getInstance(mContext)
    }

    private fun initView() {
//        cameraProviderFuture.addListener(Runnable {
//            val cameraProvider = cameraProviderFuture.get()
//            bindPreview(cameraProvider)
//        }, ContextCompat.getMainExecutor(mContext))
    }

//    /**
//     * 选择相机并绑定生命周期和用例
//     */
//    fun bindPreview(cameraProvider: ProcessCameraProvider) {
//
//        var preview: Preview = Preview.Builder()
//                .build()
//
//        var cameraSelector: CameraSelector = CameraSelector.Builder()
//                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//                .build()
//
//        preview.setSurfaceProvider(preview_view.surfaceProvider)
//
//        var camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)
//    }

}
