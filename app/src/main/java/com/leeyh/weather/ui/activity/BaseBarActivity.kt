package com.leeyh.weather.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import com.leeyh.weather.presenter.BasePresenter
import com.leeyh.weather.presenter.view.BaseView

open class BaseBarActivity<T : BasePresenter<*>> : Activity(), BaseView {
    lateinit var presenter: T

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId != android.R.id.home) {
            return super.onOptionsItemSelected(item)
        }
        finish()
        return true
    }

    protected fun setBarTitle(titleId: Int) {
        if (actionBar != null) actionBar.title = getString(titleId) else setTitle(titleId)
    }
}