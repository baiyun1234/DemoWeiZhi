package bai.bai.bai.demo.activity

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import bai.bai.bai.demo.R
import kotlinx.android.synthetic.main.activity_web_view.*
import android.webkit.WebView
import android.webkit.JsResult
import android.webkit.WebChromeClient


class WebViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


        btn_start.setOnClickListener {
                        web_view.loadUrl("https://www.baidu.com/")
//            web_view.webChromeClient = myWebChromeClient

//            web_view.loadDataWithBaseURL(null
//                    , "<html><head><title> 欢迎您 </title></head> <body><h2>< img src=\"http://gzfp.baijar.com/Content/ueditor/net/upload/image/20190323/6368893687932093921773267.png\" width=\"100%\" style=\"\" title=\"1.png\"/></p ><p><br/></p ><p>< img src=\"http://gzfp.baijar.com/Content/ueditor/net/upload/image/20190323/6368893687958657262087065.png\" width=\"100%\" style=\"\" title=\"2.png\"/></p ><p>< img src=\"http://gzfp.baijar.com/Content/ueditor/net/upload/image/20190323/6368893687943226348044789.png\" width=\"100%\" title=\"3.png\" style=\"white-space: normal;\"/></h2></body></html>"
//                    , "text/html"
//                    , "utf-8"
//                    , null)
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
