package com.huynn109.integratenative

import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp

const val FLUTTER_ENGINE_ID = "flutter_activity"
class IntegrateApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}