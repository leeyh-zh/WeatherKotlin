package com.leeyh.weather.ui.activity

import android.app.ActionBar
import android.app.Activity
import android.os.Bundle
import android.view.MenuItem

open class BaseBarActivity : Activity() {
    lateinit var bar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bar = actionBar
        bar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId != android.R.id.home) {
            return super.onOptionsItemSelected(item)
        }
        finish()
        return true
    }

    protected fun setBarTitle(titleId: Int) {
        if (bar != null) {
            bar.title = getString(titleId)
        } else {
            setTitle(titleId)
        }
    }
}