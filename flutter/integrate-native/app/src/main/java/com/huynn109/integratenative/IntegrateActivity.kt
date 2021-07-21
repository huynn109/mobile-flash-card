package com.huynn109.integratenative

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngineCache

private const val TAG_INTEGRATE_FRAGMENT = "integrate_fragment"

class IntegrateActivity : AppCompatActivity() {

    private var flutterFragment: FlutterFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_integrate)
        val fragmentManager = supportFragmentManager
        flutterFragment =
            supportFragmentManager.findFragmentByTag(TAG_INTEGRATE_FRAGMENT) as FlutterFragment?
        if (flutterFragment == null) {
            flutterFragment = if (FlutterEngineCache.getInstance().contains(FLUTTER_ENGINE_ID)) {
                FlutterFragment.withCachedEngine(FLUTTER_ENGINE_ID).build()
            } else {
                FlutterFragment.withNewEngine().build()
            }
            flutterFragment?.let {
                fragmentManager.beginTransaction()
                    .add(R.id.frameLayout, it, TAG_INTEGRATE_FRAGMENT)
                    .commit()
            }

        }
    }

    override fun onPostResume() {
        super.onPostResume()
        flutterFragment?.onPostResume()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        flutterFragment?.onNewIntent(intent)
    }

    override fun onBackPressed() {
        flutterFragment?.onBackPressed()
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
        flutterFragment?.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        flutterFragment?.onTrimMemory(level)
    }
}