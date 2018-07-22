package com.leeyh.weather.util

object StringUtils {
    val EMPTY_STRING = ""
    val WEATHER_PARTNER_SUFFIX = "&partner=1000001029"
    private val WEATHER_URL = "http://m.weathercn.com/"

    fun isBlank(string: String?): Boolean {
        if (string == null || string.isEmpty()) {
            return true
        }
        val l = string.length
        for (i in 0 until l) {
            if (!isWhitespace(string.codePointAt(i))) {
                return false
            }
        }
        return true
    }

    fun isWhitespace(c: Int): Boolean {
        return c == 32 || c == 9 || c == 10 || c == 12 || c == 13
    }

    fun getDailyMobileLink(areaCode: String, index: Int): String {
        return "http://m.weathercn.com/daily-weather-forecast.do?language=zh-cn&smartid=$areaCode&day=$index$WEATHER_PARTNER_SUFFIX"
    }

    fun getPartner(baseUrl: String?): String {
        if (baseUrl == null) {
            return ""
        }
        return if (baseUrl.contains("?")) WEATHER_PARTNER_SUFFIX else "?partner=1000001029"
    }

    fun composeDailyTemperature(highTemp: Int, lowTemp: Int): String {
        return highTemp.toString() + "° /" + lowTemp + "°"
    }

    fun getAccuMainMobileLink(areakey: String, local: String): String {
        return WEATHER_URL + local + "/cn/baoan-district/" + areakey + "/current-weather/" + areakey + "?partner=oneplusglobal"
    }

    fun getChinaMainMobileLink(areakey: String, local: String): String {
        return "http://m.weathercn.com/index.do?language=" + local + "&smartid=" + areakey + WEATHER_PARTNER_SUFFIX
    }

    fun getAqiMobileLink(areaCode: String, local: String): String {
        return "http://m.weathercn.com/air-quality.do?language=" + local + "&smartid=" + areaCode + WEATHER_PARTNER_SUFFIX
    }

    fun getLifeMobileLink(areaCode: String, local: String): String {
        return "http://m.weathercn.com/livingindex.do?language=" + local + "&smartid=" + areaCode + WEATHER_PARTNER_SUFFIX
    }

    fun getFifteendaysMobileLink(areaCode: String, local: String): String {
        return "http://m.weathercn.com/index.do?language=" + local + "&smartid=" + areaCode + WEATHER_PARTNER_SUFFIX
    }
}
