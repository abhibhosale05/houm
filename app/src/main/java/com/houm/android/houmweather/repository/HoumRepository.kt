package com.houm.android.houmweather.repository

import com.houm.android.houmweather.dto.HourlyWeatherResponse
import com.houm.android.houmweather.dto.NetworkResponse

interface HoumRepository {
    suspend fun getHourlyWeather(latitude: Double, longitude: Double,units:String): NetworkResponse<HourlyWeatherResponse, HourlyWeatherResponse>
}