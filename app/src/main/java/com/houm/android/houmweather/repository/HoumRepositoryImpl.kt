package com.houm.android.houmweather.repository

import com.houm.android.houmweather.api.WeatherApi
import com.houm.android.houmweather.dependency.IoDispatcher
import com.houm.android.houmweather.dto.HourlyWeatherResponse
import com.houm.android.houmweather.dto.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HoumRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : HoumRepository {

    companion object {
        private const val API_KEY = "a3cb7063a42d139fccdf08c1c203bc73"
    }

    override suspend fun getHourlyWeather(
        latitude: Double,
        longitude: Double,
        units: String
    ): NetworkResponse<HourlyWeatherResponse, HourlyWeatherResponse> {
        return withContext(ioDispatcher) {
            weatherApi.getHourlyWeather(
                latitude,
                longitude,
                "daily,minutely,alerts",
                units,
                API_KEY
            )
        }

    }

}