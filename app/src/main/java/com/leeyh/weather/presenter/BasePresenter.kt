package com.leeyh.weather.presenter

import com.leeyh.weather.presenter.view.BaseView

open class BasePresenter<T : BaseView> {
    lateinit var mView: T
    fun init(){
    }
}