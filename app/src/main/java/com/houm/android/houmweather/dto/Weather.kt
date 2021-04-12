package com.houm.android.houmweather.dto

import com.google.gson.annotations.SerializedName

data class Weather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: String,
    val pressure: Double,
    val  humidity: Double,
    @SerializedName("dew_point")
    val dewPoint: Double,
    val uvi: Double,
    val clouds: Double,
    val visibility: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_deg")
    val windDeg: Double,
    @SerializedName("wind_gust")
    val windGust: Double,
    val weather: List<WeatherInfo>

)
