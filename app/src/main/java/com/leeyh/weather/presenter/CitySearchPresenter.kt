package com.leeyh.weather.presenter

import com.leeyh.weather.presenter.view.CitySearchView

class CitySearchPresenter : BasePresenter<CitySearchView>() {
    fun searchCity(city: String) {
        mView.searchResult(city,true)
    }
}