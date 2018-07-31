package com.leeyh.weather

import android.app.Application
import com.leeyh.weather.extensions.DelegatesExtensions

class App : Application() {
    companion object {
        var instance: App by DelegatesExtensions.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}