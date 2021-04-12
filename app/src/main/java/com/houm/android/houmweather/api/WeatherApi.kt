package com.houm.android.houmweather.api

import com.houm.android.houmweather.dto.HourlyWeatherResponse
import com.houm.android.houmweather.dto.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(Endpoints.HOURLY_WEATHER)
    suspend fun getHourlyWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): NetworkResponse<HourlyWeatherResponse, HourlyWeatherResponse>


}
