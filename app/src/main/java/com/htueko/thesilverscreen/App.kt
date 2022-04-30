package com.htueko.thesilverscreen

import android.app.Application
import com.htueko.thesilverscreen.domain.util.AppLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppLogger.init()
    }
}
