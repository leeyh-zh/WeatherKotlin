package com.leeyh.weather.db

class CityWeatherDB private constructor() {
    companion object {
        @JvmStatic
        val instance: CityWeatherDB by lazy { CityWeatherDB() }
    }
}