package com.houm.android.houmweather.intf

interface BasePresenter <View> {
    fun getView(): View?
    fun detachView()
    fun attachView(view: View)
}