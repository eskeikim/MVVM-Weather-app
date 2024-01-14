package com.eskeitec.apps.weatherman.utils

import android.annotation.SuppressLint
import com.eskeitec.apps.weatherman.utils.Utils.Companion.DAY_TIME_FORMAT
import java.text.SimpleDateFormat
import java.util.*

object EpochConverter {
    @SuppressLint("SimpleDateFormat")
    fun readTimestamp(timestamp: Long): String {
        val formatter = SimpleDateFormat(DAY_TIME_FORMAT)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp * 1000L
        return formatter.format(calendar.time)
    }
}