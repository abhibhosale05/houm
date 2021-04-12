package com.houm.android.houmweather.feature.search

import com.houm.android.houmweather.base.AbstractBasePresenter
import com.houm.android.houmweather.common.Utils
import javax.inject.Inject

class SearchLocationPresenter @Inject constructor() : AbstractBasePresenter<SearchLocation.View>(),
    SearchLocation.Presenter {

    override fun attachView(view: SearchLocation.View) {
        super.attachView(view)
        getView()?.onInitialiseListener()
    }

    override fun isValidInput(latitude: Double?, longitude: Double?) {
        if (Utils.isValidLatLng(latitude, longitude)) {
            getView()?.onValidInput()
        } else {
            getView()?.onInvalidInput()
        }
    }


}