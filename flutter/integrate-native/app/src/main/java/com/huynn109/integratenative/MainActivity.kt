package com.huynn109.integratenative

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var flutterEngine: FlutterEngine;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flutterEngine = FlutterEngine(this)
        findViewById<Button>(R.id.btnActivity).setOnClickListener(this)
        findViewById<Button>(R.id.btnFragment).setOnClickListener(this)
        findViewById<Button>(R.id.btnView).setOnClickListener(this)
        findViewById<Button>(R.id.btnDestroyEngine).setOnClickListener(this)
        findViewById<Button>(R.id.btnPreWarm).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnActivity -> {
                preWarm()
                val intent: Intent = 
                FlutterActivity
//                            .withNewEngine()
                    .withCachedEngine(FLUTTER_ENGINE_ID)
                    .build(this)
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
                preWarm()
                Toast.makeText(this, "The engine prepared!", Toast.LENGTH_SHORT).show()
            }
            R.id.btnDestroyEngine -> {
                FlutterEngineCache.getInstance().get(FLUTTER_ENGINE_ID)?.destroy()
                FlutterEngineCache.getInstance().remove(FLUTTER_ENGINE_ID)
                Toast.makeText(this, "The engine destroy!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun preWarm() {
        if (!FlutterEngineCache.getInstance().contains(FLUTTER_ENGINE_ID)) {
            flutterEngine.apply {
                navigationChannel.setInitialRoute("/flutter-activity")
                dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
                FlutterEngineCache.getInstance().put(FLUTTER_ENGINE_ID, this)
            }
        } else {
//            flutterEngine.destroy()
        }
    }
}