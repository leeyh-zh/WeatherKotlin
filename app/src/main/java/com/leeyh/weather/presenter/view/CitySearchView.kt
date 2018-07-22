package com.leeyh.weather.presenter.view

interface CitySearchView : BaseView {
    fun searchResult(city:String,result: Boolean)
}