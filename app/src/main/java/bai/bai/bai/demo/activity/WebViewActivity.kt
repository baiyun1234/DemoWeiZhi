package bai.bai.bai.demo.activity

import android.app.Activity
import android.app.AlertDialog
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.*
import bai.bai.bai.demo.R
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


        btn_start.setOnClickListener {
//                        web_view.loadUrl("https://www.baidu.com/")

            val settings = web_view.settings
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.setSupportZoom(false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            settings.loadWithOverviewMode = true
            settings.blockNetworkImage = false
            settings.useWideViewPort = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            web_view.webViewClient = Client()

                        web_view.loadUrl("https://payin.payserv.net/redirect/wechat_alipay/GjKKa58IyoDcj6U7ftEK3X")
        }

    }

    internal inner class Client : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
//            super.onPageFinished(view, url)
            Log.d("WebViewClient","WebViewClient|onPageFinished")
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            Log.d("WebViewClient","WebViewClient|shouldOverrideUrlLoading, url = $url")
            view.loadUrl(url)
            return true
        }

//        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//            return false
//        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            Log.d("WebViewClient","WebViewClient|onReceivedError")
        }

        override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
            super.onReceivedHttpError(view, request, errorResponse)
            Log.d("WebViewClient","WebViewClient|onReceivedHttpError")
        }

        override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            Log.d("WebViewClient","WebViewClient|onReceivedError111|errorCode = $errorCode, description = $description, failingUrl = $failingUrl")
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            // 校验过程遇到了bug
            Log.d("WebViewClient","WebViewClient|onReceivedSslError|primaryError = ${error?.primaryError}")
            if (error?.primaryError == SslError.SSL_INVALID) {
                handler?.proceed()
            } else {
                handler?.cancel()
            }
        }

    }


    private val myWebChromeClient = object : WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        override fun onJsAlert(webView: WebView, url: String, message: String, result: JsResult): Boolean {
            val localBuilder = AlertDialog.Builder(webView.context)
            localBuilder.setMessage(message).setPositiveButton("确定", null)
            localBuilder.setCancelable(false)
            localBuilder.create().show()

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm()
            return true
        }

        //获取网页标题
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            Log.i("ansen", "网页标题:$title")
        }

        //加载进度回调
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            progress_bar.progress = newProgress
        }
    }

}
