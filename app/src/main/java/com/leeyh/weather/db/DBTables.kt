package com.leeyh.weather.db

object CityForecastTable {
    const val NAME = "CityForecast"
    const val ID = "_id"
    const val CID = "cid"
    const val LOCATION = "location"
    const val PARENT_CITY = "parentCity"
    const val ADMIN_AREA = "adminArea"
    const val COUNTRY = "country"
    const val TIME_ZONE = "timeZone"
    const val LAST_REFRESH_TIME = "lastRefreshTime"
}

object NowWeatherTable {
    const val NAME = "NowWeather"
    const val ID = "_id"
    const val CLOUD = "cloud"
    const val COND_CODE = "cond_code"
    const val COND_TXT = "cond_txt"
    const val FL = "fl"
    const val HUM = "hum"
    const val PCPN = "pcpn"
    const val PRES = "pres"
    const val TMP = "tmp"
    const val VIS = "vis"
    const val WIND_DEG = "wind_deg"
    const val WIND_DIR = "wind_dir"
    const val WIND_SC = "wind_sc"
    const val WIND_SPD = "wind_spd"
}

object DailyForecastTable {
    const val NAME = "DailyForecast"
    const val ID = "_id"
    const val COND_CODE_D = "cond_code_d"
    const val COND_CODE_N = "cond_code_n"
    const val COND_TXT_D = "cond_txt_d"
    const val COND_TXT_N = "cond_txt_n"
    const val DATE = "date"
    const val HUM = "hum"
    const val MR = "mr"
    const val MS = "ms"
    const val PCPN = "pcpn"
    const val POP = "pop"
    const val PRES = "pres"
    const val SR = "sr"
    const val SS = "ss"
    const val TMP_MAX = "tmp_max"
    const val TMP_MIN = "tmp_min"
    const val UV_INDEX = "uv_index"
    const val VIS = "vis"
    const val WIND_DEG = "wind_deg"
    const val WIND_DIR = "wind_dir"
    const val WIND_SC = "wind_sc"
    const val WIND_SPD = "wind_spd"
}

object HourlyForecastTable {
    const val NAME = "HourlyForecast"
    const val ID = "_id"
    const val CLOUD = "cloud"
    const val COND_CODE = "cond_code"
    const val CONUD_TXT = "cond_txt"
    const val DEW = "dew"
    const val HUM = "hum"
    const val POP = "pop"
    const val PRES = "pres"
    const val TIME = "time"
    const val TMP = "tmp"
    const val WIND_DEG = "wind_deg"
    const val WIND_DIR = "wind_dir"
    const val WIND_SC = "wind_sc"
    const val WIND_SPD = "wind_spd"
}

object LifestyleTable {
    const val NAME = "Lifestyle"
    const val ID = "_id"
    const val TYPE = "type"
    const val BRF = "brf"
    const val TXT = "txt"
}