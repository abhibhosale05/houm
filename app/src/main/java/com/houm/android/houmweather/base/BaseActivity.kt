package com.houm.android.houmweather.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.houm.android.houmweather.intf.BaseView

open class BaseActivity : AppCompatActivity(),BaseView {

    override fun getContext(): Context? = applicationContext

    override fun getActivity(): FragmentActivity? = this
}