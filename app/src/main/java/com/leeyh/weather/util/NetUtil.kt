package com.leeyh.weather.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

object NetUtil : AnkoLogger {
    private val ACCU_API_URL = "http://api.accuweather.com/"
    private val LAUNG_REGEX = "(?<=\\blanguage=\\b\\w{0,2})-\\w*"
    val NETWORK_NONE = 0
    val NETWORK_WIFI = 1
    val NETWORK_MOBILE = 2

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return !(networkInfo == null || !networkInfo.isAvailable)
    }

    fun getNetworkState(context: Context): Int {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val wifiState = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state
        if (wifiState == NetworkInfo.State.CONNECTED || wifiState == NetworkInfo.State.CONNECTING) {
            return NETWORK_WIFI
        }

        val state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return NETWORK_MOBILE
        }
        return NETWORK_NONE
    }

    fun getNetworkStates(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            val wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val dataNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已连接,移动数据已连接", Toast.LENGTH_SHORT).show();
            } else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已连接,移动数据已断开", Toast.LENGTH_SHORT).show();
            } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已断开,移动数据已连接", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "WIFI已断开,移动数据已断开", Toast.LENGTH_SHORT).show();
            }
        } else {
            val networks = connectivityManager.allNetworks
            var sb = StringBuilder()
            for (i in 0 until networks.size) {
                val networkInfo = connectivityManager.getNetworkInfo(networks[i]);
                sb.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
            }
            context.toast(sb.toString())
        }
    }


}