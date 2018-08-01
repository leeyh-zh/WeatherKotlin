package com.leeyh.weather.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.leeyh.weather.App
import org.jetbrains.anko.db.*

class CityWeatherDBHelper(context: Context = App.instance) : ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "city_list.db"
        const val DB_VERSION = 1
        val instance by lazy { CityWeatherDBHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CityForecastTable.NAME, true,
                CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.LOCATION to TEXT,
                CityForecastTable.PARENT_CITY to TEXT,
                CityForecastTable.ADMIN_AREA to TEXT,
                CityForecastTable.COUNTRY to TEXT,
                CityForecastTable.TIME_ZONE to TEXT,
                CityForecastTable.LAST_REFRESH_TIME to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}