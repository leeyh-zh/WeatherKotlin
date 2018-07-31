package com.leeyh.weather.db

import android.content.Context
import com.leeyh.weather.App

class CityWeatherDB private constructor(context: Context = App.instance) {
    companion object {
        val instance: CityWeatherDB by lazy { CityWeatherDB() }
    }
    init {

    }
}