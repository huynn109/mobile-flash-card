package com.example.androidthread

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Thread.currentThread


class LoginActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        const val MSG_UPDATE_NUMBER = 100
        const val MSG_UPDATE_NUMBER_DONE = 101
    }

    private var handler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.btnGetHandleMessage).setOnClickListener {
            ThreadLooper(this).apply {
                Log.d("ThreadLooper", "Message: ${Thread.currentThread().name}")
                start()
                for (i in 0 until 10) {
                    Thread.sleep(300)
                    this.handler?.sendMessage(
                        Message.obtain().apply { obj = "Send from UI thread $i" })
                }
            }
//            val thread = PrimeThread(200, this)
//            thread.start()
//            val threadRunnable = PrimeThreadRunnable(200, this)
//            Thread(threadRunnable).start()
        }
    }

}

class PrimeThread(var minPrime: Long, val context: Context) : Thread(), Runnable {
    companion object {
        const val TAG = "PrimeThread"
    }

    override fun run() {
        Log.d(TAG, "run: ${currentThread().name}") // Background thread
        Log.d(TAG, "run: $minPrime")
//        Toast.makeText(context, currentThread().name, Toast.LENGTH_SHORT).show() // Crash: call UI from background thread
    }
}

class PrimeThreadRunnable(var minPrime: Long, val context: Context) :Runnable {
    companion object {
        const val TAG = "PrimeThread"
    }

    override fun run() {
        Log.d(TAG, "run: ${currentThread().name}") // Background thread
        Log.d(TAG, "run: $minPrime")
//        Toast.makeText(context, currentThread().name, Toast.LENGTH_SHORT).show() // Crash: call UI from background thread
    }
}

class ThreadLooper(var context: Context) : Thread() {
    var handler: Handler? = null
    override fun run() {
        // Preparing looper it creates looper
        // and initializes the queue
        Looper.prepare()
        handler =
            @SuppressLint("HandlerLeak")
            object : Handler() {
                override fun handleMessage(msg: Message) {
                    Log.d("ThreadLooper", "Message: ${msg.obj}")
                    Log.d("ThreadLooper", "CurrentThread: ${currentThread().name}")
//                    Toast.makeText(context, currentThread().name, Toast.LENGTH_SHORT).show()
                }
            }
        //To start looping
        Looper.loop()
    }
}