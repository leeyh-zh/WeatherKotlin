package com.leeyh.weather.ui.activity

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import com.leeyh.weather.R
import com.leeyh.weather.presenter.CitySearchPresenter
import com.leeyh.weather.presenter.view.CitySearchView
import com.leeyh.weather.util.AlertUtil
import com.leeyh.weather.util.NetUtil
import kotlinx.android.synthetic.main.search_citylist_activity.*
import kotlinx.android.synthetic.main.search_citylist_bar.*
import org.jetbrains.anko.toast

class CitySearchActivity : BaseBarActivity<CitySearchPresenter>(), CitySearchView {

    override fun searchResult(city: String, result: Boolean) {
        toast("$city $result")
    }

    private var mReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent!!.action == ConnectivityManager.CONNECTIVITY_ACTION) {
                val networkInfo = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
                if (networkInfo == null || !networkInfo.isAvailable) {
                    mNoSearchView.text = getString(R.string.no_network_statu)
                    mNoSearchView.setCompoundDrawablesWithIntrinsicBounds(null, context!!.getDrawable(R.drawable
                            .no_network), null, null)
                    return
                }
                noConnectionDialog!!.dismiss()
                mNoSearchView.text = getString(R.string.no_search_data)
                mNoSearchView.setCompoundDrawablesWithIntrinsicBounds(null, context!!.getDrawable(R.drawable
                        .no_search_icon), null, null)
            }
        }
    }
    var noConnectionDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_citylist_activity)
        registerReceiver()
        noConnectionDialog = AlertUtil.noConnectionDialog(this)
        if (!NetUtil.isNetworkAvailable(this)) {
            noConnectionDialog!!.show()
        }
        presenter = CitySearchPresenter()
        presenter.mView = this
        presenter.searchCity("zhuhai")
        initUIView()
    }

    private fun initUIView() {
        if (actionBar != null) {
            val actionbarLayout = LayoutInflater.from(this).inflate(R.layout.search_citylist_bar, null)
            actionBar.setDisplayShowCustomEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.customView = actionbarLayout
            mCityKeyword.isFocusable = true
            mCityKeyword.isFocusableInTouchMode = true
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(mCityKeyword, InputMethodManager.SHOW_FORCED)
            mCityKeyword.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun registerReceiver() {
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(mReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mReceiver != null) {
            unregisterReceiver(mReceiver)
            mReceiver = null
        }
    }
}