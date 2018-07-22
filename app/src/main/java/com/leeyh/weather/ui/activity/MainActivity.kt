package com.leeyh.weather.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.leeyh.weather.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupActionBar()
    }

    fun setupActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbarGps.setOnClickListener({
            Toast.makeText(this, "gps", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.action_cities -> gotoCityList()

        }

        return super.onOptionsItemSelected(item)
    }

    private fun gotoCityList() {
        startActivityForResult(Intent(this, CityListActivity::class.java), 1)
        overridePendingTransition(R.anim.citylist_translate_up, R.anim.alpha_out)
    }

}
