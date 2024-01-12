package com.eskeitec.apps.weatherman

import android.app.Application
import androidx.multidex.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WeatherManApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "Class:%s: Line: %s, Method: %s",
                        super.createStackElementTag(element),
                        element.lineNumber,
                        element.methodName,
                    )
                }
            })
        }
    }
}
