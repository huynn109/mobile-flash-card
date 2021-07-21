package com.huynn109.androidcore

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnLifecycle).setOnClickListener(this)
        findViewById<Button>(R.id.btnBroadcast).setOnClickListener(this)
        findViewById<Button>(R.id.btnContentProvider).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBroadcast -> {
                this.startActivity(Intent(this, BroadcastActivity::class.java))
            }
            R.id.btnContentProvider -> {
                this.startActivity(Intent(this, ContentProviderActivity::class.java))
            }
            R.id.btnLifecycle -> {
                this.startActivity(Intent(this, ActivityLifecycle::class.java))
            }
        }
    }
}
