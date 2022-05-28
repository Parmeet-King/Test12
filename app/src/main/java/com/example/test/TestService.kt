package com.example.test

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TestService : Service() {
    val TAG = "TestService"

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG,"OnStartCommand")
        // also can give countDownTimer for 10 mins
        return START_STICKY
    }

    //NOTE: in some mobile phone companies like XIAOMI, HUAWEI, LG :  you need to turn off power saver for this app in order for this service to work

    //set code for logout service here

    override fun onTaskRemoved(rootIntent: Intent?) {
        //Logout User from the database after 10 mins
        super.onTaskRemoved(rootIntent)
    }
}