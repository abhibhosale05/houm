package com.houm.android.houmweather.common

object Utils {

    fun isValidLatLng(lat: Double?, lng: Double?): Boolean {

        if (lat != null && lng != null) {
            if (lat < -90 || lat > 90) {
                return false
            } else if (lng < -180 || lng > 180) {
                return false
            }
            return true
        }
        return false
    }
}