package com.leeyh.weather.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.leeyh.weather.App
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class CityWeatherDBHelper(context: Context = App.instance) : ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "city_list.db"
        const val DB_VERSION = 1
        val instance by lazy { CityWeatherDBHelper() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}