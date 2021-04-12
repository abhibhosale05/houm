package com.houm.android.houmweather.feature.dashboard

import com.houm.android.houmweather.base.AbstractBasePresenter
import com.houm.android.houmweather.dependency.IoDispatcher
import com.houm.android.houmweather.dependency.MainDispatcher
import com.houm.android.houmweather.dto.NetworkResponse
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DashboardPresenter @Inject constructor(
    private val model: Dashboard.Model,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : AbstractBasePresenter<Dashboard.View>(), Dashboard.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + ioDispatcher

    override fun attachView(view: Dashboard.View) {
        super.attachView(view)
        getView()?.onInitialiseListener()
    }

    override fun getHourlyWeather(latitude: Double, longitude: Double, units: String) {
        getView()?.onStartLoading()
        launch {
            val networkLogoutResponse = model.getHourlyWeather(latitude, longitude, units)
            withContext(mainDispatcher) {
                getView()?.onStopLoading()
                when (networkLogoutResponse) {
                    is NetworkResponse.Success -> {
                        val logoutResponse = networkLogoutResponse.body
                        getView()?.onCurrentWeather(logoutResponse.current)
                        getView()?.onHourlyWeathers(logoutResponse.hourly)
                    }
                    is NetworkResponse.ApiError -> {
                    }
                    is NetworkResponse.NetworkError -> {
                    }
                    is NetworkResponse.UnknownError -> {
                    }
                }
            }
        }
    }

}