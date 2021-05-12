package com.huynn109.androidcore

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class MyBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val TAG = "MyBroadcastReceiver"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action?.intern() == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            Toast.makeText(
                context,
                "onReceive airplane mode ${intent.getBooleanExtra("state", false)}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}