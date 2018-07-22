package com.leeyh.weather.ui.activity

import android.app.Activity
import com.leeyh.weather.presenter.BasePresenter
import com.leeyh.weather.presenter.view.BaseView

open class BaseMvpActivity<T : BasePresenter<*>> : Activity(), BaseView {

    lateinit var presenter: T

    override fun initView() {

    }
}