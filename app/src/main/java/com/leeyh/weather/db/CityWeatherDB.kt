package com.leeyh.weather.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
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

    fun addCity(cid: String, location: String, parentCity: String, adminArea: String, country: String,
                timeZone: String, lastRefreshTime: String): Long {
        val contentValues = ContentValues()
        if (DatabaseUtils.queryNumEntries(mDBHelper.writableDatabase, CityForecastTable.NAME).toInt() == 0) {
            contentValues.put(CityForecastTable.ID, 1)
        }
        contentValues.put(CityForecastTable.CID, cid)
        contentValues.put(CityForecastTable.LOCATION, location)
        contentValues.put(CityForecastTable.PARENT_CITY, parentCity)
        contentValues.put(CityForecastTable.ADMIN_AREA, adminArea)
        contentValues.put(CityForecastTable.COUNTRY, country)
        contentValues.put(CityForecastTable.TIME_ZONE, timeZone)
        contentValues.put(CityForecastTable.LAST_REFRESH_TIME, lastRefreshTime)
        val recordId = mDBHelper.writableDatabase.insert(CityForecastTable.NAME, null, contentValues)
        if (recordId >= 0) {
            //TODO triggerDataChangeListener
        }
        return recordId
    }

    fun addCity(cid: String, location: String, parentCity: String, adminArea: String, country: String,
                timeZone: String, lastRefreshTime: String, checkUnique: Boolean): Long {
        if (checkUnique) {
            val cursor = mDBHelper.writableDatabase.query("city", null, "locationId = ?",
                    arrayOf(cid), null, null, "displayOrder ASC", null)
            if (cursor != null && cursor.count > 0 && cursor.moveToFirst()) {
                if (cursor.count != 1) {
                    while (cursor.moveToNext()) {
                        if (cursor.getString(2) == location) {
                            cursor.close()
                            return -1
                        }
                    }
                } else if (cursor.getString(4) == cid) {
                    cursor.close()
                    return -1
                }
            }
        }
        return addCity(cid, location, parentCity, adminArea, country, timeZone, lastRefreshTime)
    }

}