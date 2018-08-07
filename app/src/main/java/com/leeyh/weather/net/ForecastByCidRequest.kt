package com.leeyh.weather.net

import com.google.gson.Gson
import java.net.URL

class ForecastByCidRequest(var cid: String, val gson: Gson = Gson()) {
    companion object {
        private const val KEY = "2a814ee447aa412496cd865520cf07c9"
        private const val BASE_URL = "https://free-api.heweather.com/s6/"
        private const val WEATHER_PARAMETERS = "?location="
    }

    fun execute(): ForecastResult {
        val url = "$BASE_URL$WEATHER_PARAMETERS?key=$cid"
        val forecastResult = URL(url).readText()
        return gson.fromJson(forecastResult, ForecastResult::class.java)
    }
}