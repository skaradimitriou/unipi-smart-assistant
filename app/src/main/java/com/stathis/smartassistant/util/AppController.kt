package com.stathis.smartassistant.util

import android.app.Application
import timber.log.Timber

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}