package com.houm.android.houmweather.dto

import com.google.gson.annotations.SerializedName

data class HourlyWeatherResponse(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: Long,
    val current: Weather?,
    val hourly: List<Weather>?
)
