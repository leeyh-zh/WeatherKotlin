package com.leeyh.weather.db

import android.annotation.SuppressLint
import android.content.Context
import com.leeyh.weather.App

class CityWeatherDB private constructor(context: Context = App.instance,
                                        cityWeatherDBHelper: CityWeatherDBHelper = CityWeatherDBHelper.instance) {
    private var mDBHelper: CityWeatherDBHelper = cityWeatherDBHelper

    companion object {
        val instance: CityWeatherDB by lazy { CityWeatherDB() }
    }

    @SuppressLint("Recycle")
    fun getMaxIDValue(): Int {
        val cursor = mDBHelper.writableDatabase.query(CityForecastTable.NAME,
                null, null, null, null, null, "_id DESC LIMIT 0,1")
        return if (cursor == null || cursor.moveToFirst()) 0 else cursor.getInt(cursor.getColumnIndex(CityForecastTable.ID))
    }

}