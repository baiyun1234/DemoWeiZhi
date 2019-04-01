package bai.bai.bai.demo.activity

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableString
import bai.bai.bai.demo.R
import kotlinx.android.synthetic.main.activity_count_down.*
import android.text.Spanned
import android.text.style.ForegroundColorSpan

class CountDownActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down)

        var countDown = 10
        setText(countDown)
        btn_start.setOnClickListener {
            object : CountDownTimer(11 * 1000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    countDown--
                    setText(countDown)
                }

                override fun onFinish() {
                }

            }.start()
        }

    }

    private fun setText(countDown: Int) {
        val spannableString = SpannableString(resources.getString(R.string.count_down, countDown))
        val colorSpan = ForegroundColorSpan(Color.parseColor("#ff3300"))
        spannableString.setSpan(colorSpan, spannableString.length - 4, spannableString.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        tv_count_down.text = spannableString
    }
}
