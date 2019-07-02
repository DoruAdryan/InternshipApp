package com.cgminternship.internshipapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        Log.d("MyService", "onCreate called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId).also {
            Log.d("MyService", "onStartCommand called")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MyService", "onDestroy called")
    }
}