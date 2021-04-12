package com.houm.android.houmweather.feature.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.houm.android.houmweather.R
import com.houm.android.houmweather.adapter.HourlyWeatherListAdapter
import com.houm.android.houmweather.api.Endpoints
import com.houm.android.houmweather.base.BaseActivity
import com.houm.android.houmweather.dto.Weather
import com.houm.android.houmweather.feature.search.SearchLocationActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : BaseActivity(), Dashboard.View {

    private var lat: Double = 18.5204
    private var lng: Double = 73.8567

    companion object {
        private const val SEARCH_LOCATION_REQUEST_CODE = 1001
        private const val UNITS = "metric"
        const val LATITUDE_KEY = "lat"
        const val LONGITUDE_KEY = "lng"
    }

    @Inject
    lateinit var presenter: DashboardPresenter

    private var hourlyWeatherListAdapter: HourlyWeatherListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        presenter.attachView(this)
    }


    override fun onInitialiseListener() {
        hourly_weather_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hourlyWeatherListAdapter = HourlyWeatherListAdapter(emptyList<Weather>(), this)
        hourly_weather_recyclerview.adapter = hourlyWeatherListAdapter
        latlng_textview.setOnClickListener { openSearchLocationActivity() }
        loadWeatherData()
    }

    private fun openSearchLocationActivity() {
        startActivityForResult(
            Intent(this, SearchLocationActivity::class.java),
            SEARCH_LOCATION_REQUEST_CODE
        )
    }

    override fun onStartLoading() {
        progress_circular.visibility = View.VISIBLE
        nested_scrollview.visibility = View.GONE
    }

    override fun onStopLoading() {
        progress_circular.visibility = View.GONE
    }

    override fun onCurrentWeather(current: Weather?) {
        val decimalFormat = DecimalFormat("#.#")
        current?.let { currentWeather ->
            Picasso.get().load(String.format(Endpoints.ICON_URL, currentWeather.weather[0].icon))
                .into(description_imageview)
            description_textview.text = currentWeather.weather[0].description
            temp_textview.text = String.format(
                getString(R.string.str_temp),
                decimalFormat.format(currentWeather.temp)
            )
            feels_like_textview.text =
                String.format(getString(R.string.str_feels_like), currentWeather.feelsLike)
            wind_textview.text =
                String.format(
                    getString(R.string.str_wind),
                    decimalFormat.format(currentWeather.windSpeed)
                )
            pressure_textview.text =
                String.format(
                    getString(R.string.str_pressure),
                    decimalFormat.format(currentWeather.pressure)
                )
            visibility_textview.text =
                String.format(
                    getString(R.string.str_visibility),
                    decimalFormat.format(currentWeather.visibility)
                )
            uvindextextview.text =
                String.format(getString(R.string.str_uv), decimalFormat.format(currentWeather.uvi))
            devpoint_textview.text =
                String.format(
                    getString(R.string.str_devpoint),
                    decimalFormat.format(currentWeather.dewPoint)
                )
            humidity_textview.text =
                "${
                    String.format(
                        getString(R.string.str_humidity),
                        decimalFormat.format(currentWeather.humidity)
                    )
                }%"
        }
        nested_scrollview.visibility = View.VISIBLE
    }

    override fun onHourlyWeathers(hourly: List<Weather>?) {
        hourlyWeatherListAdapter?.updateList(hourly)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SEARCH_LOCATION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                lat = data.getDoubleExtra(LATITUDE_KEY, lat)
                lng = data.getDoubleExtra(LONGITUDE_KEY, lng)
            }
            loadWeatherData()
        }

    }

    private fun loadWeatherData() {
        latlng_textview.text = "${lat},${lng}"
        presenter.getHourlyWeather(lat, lng, UNITS)
    }

}