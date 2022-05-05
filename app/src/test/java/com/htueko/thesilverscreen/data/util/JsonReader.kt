package com.htueko.thesilverscreen.data.util

import androidx.test.platform.app.InstrumentationRegistry
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

object JsonReader {
    fun getJson(path: String): String {
        return try {
            val context = InstrumentationRegistry.getInstrumentation().context
            val jsonStream: InputStream = context.assets.open("networkresponses/$path")
            String(jsonStream.readBytes())
        } catch (exception: IOException) {
            Timber.e("getJson: Error reading network response json asset")
            throw exception
        }
    }
}
