package com.leeyh.weather.net

data class ForecastResult(val HeWeather6: HeWeather6)

data class HeWeather6(val basic: Basic, val update: Update, val now: Now, val dailyForecast: DailyForecast,
                      val hourlyForecast: HourlyForecast, val lifestyleForecast: LifestyleForecast)

data class Basic(val cid: String, val location: String, val parent_city: String, val admin_area: String, val cnty: String, val tz: String)

data class Update(val loc: String)

data class Now(val cloud: String, val cond_code: String, val cond_txt: String, val fl: String, val hum: String,
               val pcpn: String, val pres: String, val tmp: String, val vis: String, val wind_deg: String,
               val wind_dir: String, val wind_sc: String, val wind_spd: String)

data class DailyForecast(val daily: List<Daily>)

data class Daily(
        val cond_code_d: String, val cond_code_n: String, val cond_txt_d: String, val cond_txt_n: String,
        val date: String, val hum: String, val mr: String, val ms: String, val pcpn: String, val pop: String,
        val pres: String, val sr: String, val ss: String, val tmp_max: String, val tmp_min: String, val uv_index: String,
        val vis: String, val wind_deg: String, val wind_dir: String, val wind_sc: String, val wind_spd: String)

data class HourlyForecast(val hourly: List<Hourly>)

data class Hourly(val cloud: String, val cond_code: String, val cond_txt: String, val dew: String, val hum: String,
                  val pop: String, val pres: String, val time: String, val tmp: String, val wind_deg: String,
                  val wind_dir: String, val wind_sc: String, val wind_spd: String

)

data class LifestyleForecast(val lifestyle: List<Lifestyle>)

data class Lifestyle(val type: String, val brf: String, val txt: String)
