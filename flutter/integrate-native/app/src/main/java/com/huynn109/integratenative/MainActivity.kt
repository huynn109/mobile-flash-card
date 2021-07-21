package com.huynn109.integratenative

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnActivity).setOnClickListener(this)
        findViewById<Button>(R.id.btnFragment).setOnClickListener(this)
        findViewById<Button>(R.id.btnView).setOnClickListener(this)
        findViewById<Button>(R.id.btnDestroyEngine).setOnClickListener(this)
        findViewById<Button>(R.id.btnPreWarm).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnActivity -> {
                val intent: Intent = when {
                    FlutterEngineCache.getInstance().contains(FLUTTER_ENGINE_ID) -> {
                        FlutterActivity
                            .withCachedEngine(FLUTTER_ENGINE_ID)
                            .build(this)
                    }
                    else -> {
                        FlutterActivity
                            .withNewEngine()
                            .build(this)
                    }
                }
                startActivity(
                    intent
                )
            }
            R.id.btnFragment -> {
                startActivity(Intent(this, IntegrateActivity::class.java))
            }
            R.id.btnView -> {
            }
            R.id.btnPreWarm -> {
                preWarm(applicationContext)
                Toast.makeText(this, "The engine prepared!", Toast.LENGTH_SHORT).show()
            }
            R.id.btnDestroyEngine -> {
                FlutterEngineCache.getInstance().get(FLUTTER_ENGINE_ID)?.destroy()
                FlutterEngineCache.getInstance().remove(FLUTTER_ENGINE_ID)
                Toast.makeText(this, "The engine destroy!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun preWarm(context: Context) {
        if (!FlutterEngineCache.getInstance().contains(FLUTTER_ENGINE_ID)) {
            FlutterEngine(context).apply {
                navigationChannel.setInitialRoute("/flutter-activity")
                dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
                FlutterEngineCache.getInstance().put(FLUTTER_ENGINE_ID, this)
            }
        }
    }
}