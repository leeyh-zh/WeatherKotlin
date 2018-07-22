package com.leeyh.weather.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.leeyh.weather.R
import kotlinx.android.synthetic.main.citylist_activity.*

class CityListActivity : Activity() {
    val DEFAULT_CITY = "default_city"
    val INTENT_SEARCH_CITY = "search_city"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.citylist_activity)
        addCityBtn.setOnClickListener {
            startActivity(Intent(this, CitySearchActivity::class.java))
        }
    }
}