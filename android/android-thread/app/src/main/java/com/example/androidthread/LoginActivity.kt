package com.example.androidthread

import android.annotation.SuppressLint
import android.os.*
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


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
           ThreadLooper().apply {
                start()
                for (i in 0 until 10) {
                    Thread.sleep(300)
                    this.handler?.sendMessage(Message.obtain().apply { obj = "Send from UI thread $i" })
                }
            }
        }
    }

}

class ThreadLooper : HandlerThread("Test-Thread") {
    var handler: Handler? = null
    override fun run() {
        // Preparing looper it creates looper
        // and initializes the queue
        Looper.prepare()
        handler =
            @SuppressLint("HandlerLeak")
            object : Handler() {
                override fun handleMessage(msg: Message) {
                    Log.d("ThreadLooper", "handleMessage: ${msg.obj}")
                    Log.d("ThreadLooper", "handleMessage: ${currentThread().name}")
                }
            }
        //To start looping
        Looper.loop()
    }
}