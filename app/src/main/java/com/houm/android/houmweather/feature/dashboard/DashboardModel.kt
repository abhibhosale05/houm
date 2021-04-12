package com.houm.android.houmweather.feature.dashboard

import android.content.Context
import com.houm.android.houmweather.repository.HoumRepository

class DashboardModel(val context: Context, val houmRepository: HoumRepository) : Dashboard.Model {


    override suspend fun getHourlyWeather(latitude: Double, longitude: Double, units: String) = houmRepository.getHourlyWeather(latitude, longitude, units)


}