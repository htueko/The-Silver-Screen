package com.htueko.thesilverscreen

import android.app.Application
import com.htueko.logging.AppLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppLogger.init()
    }
}
