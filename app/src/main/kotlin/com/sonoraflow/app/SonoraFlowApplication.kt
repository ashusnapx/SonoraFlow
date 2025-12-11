package com.sonoraflow.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SonoraFlowApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize logging, etc.
    }
}
