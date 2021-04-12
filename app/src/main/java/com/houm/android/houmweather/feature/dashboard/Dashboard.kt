package com.houm.android.houmweather.feature.dashboard

import com.houm.android.houmweather.dto.HourlyWeatherResponse
import com.houm.android.houmweather.dto.NetworkResponse
import com.houm.android.houmweather.dto.Weather
import com.houm.android.houmweather.intf.BasePresenter
import com.houm.android.houmweather.intf.BaseView

interface Dashboard {
    interface View : BaseView {
        fun onInitialiseListener()
        fun onStartLoading()
        fun onStopLoading()
        fun onCurrentWeather(current: Weather?)
        fun onHourlyWeathers(hourly: List<Weather>?)

    }

    interface Model {
        suspend fun getHourlyWeather(latitude: Double, longitude: Double,units:String): NetworkResponse<HourlyWeatherResponse, HourlyWeatherResponse>
    }

    interface Presenter : BasePresenter<View> {
        fun getHourlyWeather(latitude: Double, longitude: Double,units:String)
    }


}