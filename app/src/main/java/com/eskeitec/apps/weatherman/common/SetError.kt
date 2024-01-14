package com.eskeitec.apps.weatherman.common

import com.eskeitec.apps.weatherman.utils.Constants

object SetError {
    fun setErrorCard(errorTitle: String): String {
        return when (errorTitle) {
            ExceptionError.GPS_DISABLED -> ExceptionErrorMessage.GPS_DISABLED_DESCR
            ExceptionError.NO_INTERNET_CONNECTION -> ExceptionErrorMessage.NO_INTERNET_CONNECTION_DESCR
            ExceptionError.NO_PERMISSION -> ExceptionErrorMessage.NO_PERMISSION_DESCR
            else -> Constants.UNKNOWN_ERROR
        }
    }
}
