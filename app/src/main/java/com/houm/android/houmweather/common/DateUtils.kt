package com.houm.android.houmweather.common

import java.text.SimpleDateFormat

object DateUtils {

    fun getHour(timeStamp: Long): String? {
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(timeStamp)
    }
}