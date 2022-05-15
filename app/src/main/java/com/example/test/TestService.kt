package com.example.test

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log

class TestService : Service() {
    val TAG = "TestService"

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        object : CountDownTimer(20000,1000) {
            override fun onTick(p0: Long) {
                Log.e(TAG,"onTick : "+p0/1000)
            }
            override fun onFinish() {
                Log.e(TAG,"onFinish : ")
            }
        }.start()
        return START_STICKY
    }
}