package com.huynn109.integratenative

import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var flutterEngine: FlutterEngine

    companion object {
        lateinit var flutterActivity: Intent
        var mResult: MethodChannel.Result? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flutterEngine = FlutterEngine(this)
        findViewById<Button>(R.id.btnActivity).setOnClickListener(this)
        findViewById<Button>(R.id.btnFragment).setOnClickListener(this)
        findViewById<Button>(R.id.btnView).setOnClickListener(this)
        findViewById<Button>(R.id.btnDestroyEngine).setOnClickListener(this)
        findViewById<Button>(R.id.btnPreWarm).setOnClickListener(this)
        handleMethodChannel()
    }

    private fun handleMethodChannel() {
        MethodChannel(flutterEngine.dartExecutor, "com.huynn109/native")
            .setMethodCallHandler { call, result ->
                when (call.method) {
                    "getBatteryLevel" -> {
                        val batteryLevel = getBatteryLevel()
                        result.success(batteryLevel)
                    }
                }
            }
    }

    private fun getBatteryLevel(): Int {
        val batteryLevel: Int
        if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            val batteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else {
            val intent = ContextWrapper(applicationContext).registerReceiver(
                null,
                IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            )
            batteryLevel =
                intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 / intent.getIntExtra(
                    BatteryManager.EXTRA_SCALE,
                    -1
                )
        }

        return batteryLevel
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnActivity -> {
                preWarm()
                flutterActivity =
                    FlutterActivity
//                            .withNewEngine()
                        .withCachedEngine(FLUTTER_ENGINE_ID)
                        .build(this)
                startActivity(
                    flutterActivity
                )
            }
            R.id.btnFragment -> {
                startActivity(Intent(this, IntegrateActivity::class.java).apply {
                    putExtra(IntegrateActivity.KEY_IS_FLUTTER_FRAGMENT, true)
                })
            }
            R.id.btnView -> {
                startActivity(Intent(this, IntegrateActivity::class.java).apply {
                    putExtra(IntegrateActivity.KEY_IS_FLUTTER_FRAGMENT, false)
                })
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
                dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
                FlutterEngineCache.getInstance().put(FLUTTER_ENGINE_ID, this)
            }
        } else {
//            flutterEngine.destroy()
        }
    }
}