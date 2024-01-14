package com.eskeitec.apps.weatherman.common

object ExceptionError {
    const val NO_PERMISSION = "No Location Permission"
    const val GPS_DISABLED = "Location is Disabled"
    const val NO_INTERNET_CONNECTION = "No Internet Connection"
    const val UNKNOWN_ERROR = "Unknown Error"
}

object ExceptionErrorMessage {
    const val NO_PERMISSION_DESCR = "Allow Location to enable app to fetch weather data."
    const val GPS_DISABLED_DESCR = "Your Location is disabled, kindly enable it."
    const val NO_INTERNET_CONNECTION_DESCR = "Please check your internet connection."
}
