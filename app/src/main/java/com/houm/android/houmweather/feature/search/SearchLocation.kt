package com.houm.android.houmweather.feature.search

import com.houm.android.houmweather.intf.BasePresenter
import com.houm.android.houmweather.intf.BaseView

interface SearchLocation {

    interface View : BaseView {
        fun onInitialiseListener()
        fun onValidInput()
        fun onInvalidInput()

    }


    interface Presenter : BasePresenter<View> {
        fun isValidInput(latitude: Double?, longitude: Double?)
    }

}