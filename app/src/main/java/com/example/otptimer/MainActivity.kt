package com.example.otptimer

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var isCancelled = false
    val minute: Long = 1000 * 5//put time in seconds
    val millisInFuture: Long = (minute * 1) + (minute * 0) + (1000 * 0)
    val countDownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timer(millisInFuture, countDownInterval).start()
        tv_resend.setOnClickListener {
            timer(millisInFuture, countDownInterval).start()
        }

    }

    private fun timer(millisInFuture: Long, countDownInterval: Long): CountDownTimer {
        return object : CountDownTimer(millisInFuture, countDownInterval) {
            @SuppressLint("ResourceAsColor")
            override fun onTick(millisUntilFinished: Long) {
                val timeRemaining = timeString(millisUntilFinished)
                if (isCancelled) {
                    Log.e("timeRemainingif", timeRemaining)
                    cancel()
                } else {
                    tv_seconds.text = timeRemaining
                    Log.e("timeRemaining", timeRemaining)
                    tv_resend.isEnabled=false
                }
            }

            @SuppressLint("ResourceAsColor")
            override fun onFinish() {
                Log.e("resend_now", "onFinish")
                tv_resend.isEnabled=true

            }
        }
    }

    private fun timeString(millisUntilFinished: Long): String {
        var millisUntilFinished: Long = millisUntilFinished
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
        millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
        return String.format(
                Locale.getDefault(),
                "%02d:%02d sec",
                minutes, seconds
        )
    }

}
