package com.huynn109.androidcore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

const val TAG = "ActivityLifecycle";

class ActivityLifecycle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
//        finish() // Gọi đến onDestroy
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "~~> onRestart")
//        finish() // Gọi đến onDestroy như khi finish trong onCreate
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "~~> onStart")
//        finish() // Gọi từ onStop
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "~~> onResume")
//        finish() // Gọi từ onPause
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "~~> onPause")
//        finish() // Gọi từ onStop
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "~~> onStop")
//        finish() // Gọi đến onDestroy
    }

    override fun onDestroy() {
        Log.d(TAG, "~~> onDestroy1")
        super.onDestroy()
        Log.d(TAG, "~~> onDestroy2")
    }
}
