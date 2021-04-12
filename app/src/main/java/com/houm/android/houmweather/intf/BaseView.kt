package com.houm.android.houmweather.intf

import android.content.Context
import androidx.fragment.app.FragmentActivity

interface BaseView {
    fun getContext(): Context?
    fun getActivity(): FragmentActivity?
}