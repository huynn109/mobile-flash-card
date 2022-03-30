package com.huynn109.integratenative

import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode
import io.flutter.plugin.common.MethodChannel

private const val TAG_INTEGRATE_FRAGMENT = "integrate_fragment"

class IntegrateActivity : AppCompatActivity() {

    private var flutterFragment: FlutterFragment? = null
    var isFlutterFragment = false

    companion object {
        const val TAG = "IntegrateActivity"
        const val KEY_IS_FLUTTER_FRAGMENT = "KEY_IS_FLUTTER_FRAGMENT"
        var result: MethodChannel.Result? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_integrate)
        isFlutterFragment = intent.getBooleanExtra(KEY_IS_FLUTTER_FRAGMENT, false) == true
        if (isFlutterFragment) {
            addFlutterFragment()
        } else {
            addFragmentView()
        }
    }

    private fun addFragmentView() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, IntegrateFragment.newInstance())
            .commitAllowingStateLoss()
    }

    private fun addFlutterFragment() {
        flutterFragment = FlutterFragment.withNewEngine().renderMode(RenderMode.texture).build()
        flutterFragment?.let {
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, it, TAG_INTEGRATE_FRAGMENT)
                .commit()
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        handleMethodChannel()

    }

    private fun handleMethodChannel() {
        flutterFragment?.flutterEngine?.dartExecutor?.binaryMessenger?.let {
            MethodChannel(it, "com.huynn109/native")
                .setMethodCallHandler { call, result ->
                    when (call.method) {
                        "getBatteryLevel" -> {
                            val batteryLevel = getBatteryLevel()
                            result.success(batteryLevel)
                        }
                    }
                }
        }
    }

    private fun getBatteryLevel(): Int {
        val batteryLevel: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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

    override fun onPostResume() {
        super.onPostResume()
        if (isFlutterFragment) flutterFragment?.onPostResume()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (isFlutterFragment) flutterFragment?.onNewIntent(intent)
    }

    override fun onBackPressed() {
        if (isFlutterFragment) flutterFragment?.onBackPressed()
        else super.onBackPressed()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        flutterFragment?.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )
    }

    override fun onUserLeaveHint() {
        if (isFlutterFragment) flutterFragment?.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (isFlutterFragment) flutterFragment?.onTrimMemory(level)
    }
}