package com.leeyh.weather.util

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.leeyh.weather.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

object AlertUtil {

    fun noConnectionDialog(context: Context): Dialog {
        return context.alert(context.getString(R.string.warning_string_no_network)) {
            yesButton { context.startActivity(Intent(Settings.ACTION_SETTINGS)) }
            noButton {}
        }.build()
    }

}