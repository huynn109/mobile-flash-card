package com.example.androidthread

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ThreadApplication: Application() {
    val executorService : ExecutorService = Executors.newFixedThreadPool(4)
}