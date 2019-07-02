package com.cgminternship.internshipapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val CUSTOM_ACTION = "myAction"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MyBroadcastReceiver", "onReceive called")
    }

}