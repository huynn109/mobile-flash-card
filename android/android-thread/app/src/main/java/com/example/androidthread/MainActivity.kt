package com.example.androidthread

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnGetHandleMessage).setOnClickListener {
            val thread = object : Thread() {
                override fun run() {
                    val msg = Message.obtain()
                    handler.sendMessage(msg)
                }
            }
            thread.start()
        }
    }

    val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            Toast.makeText(this@MainActivity, "Hello $msg", Toast.LENGTH_SHORT).show()
        }
    }
}