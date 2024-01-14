package com.eskeitec.apps.weatherman.utils

import androidx.compose.ui.graphics.Color
import com.eskeitec.apps.weatherman.R
import com.eskeitec.apps.weatherman.domain.model.WeatherType
import com.eskeitec.apps.weatherman.ui.theme.cloudyBg
import com.eskeitec.apps.weatherman.ui.theme.rainyBg
import com.eskeitec.apps.weatherman.ui.theme.sunnyBg
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Utils {

    companion object {
        val SIMPLE_DATE_TIME_FORMAT = "dd-MMM-yyyy hh:mm:ss a"
        val HOUR_TIME_FORMAT = "hh a"
        val DATE_TIME_FORMAT = "MMM dd"
        val DAY_TIME_FORMAT = "EEEE hh:mm a"

        fun getDateFromTime(time: Long, targetFormat: String): String? {
            return try {
                SimpleDateFormat(targetFormat, Locale.getDefault()).format(time)
            } catch (e: Exception) {
                Timber.d(e.localizedMessage, e); null
            }
        }

        fun String.toFormattedDate(): String {
            val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputDateFormat = SimpleDateFormat("MMM d", Locale.getDefault())

            try {
                val date = inputDateFormat.parse(this)
                if (date != null) {
                    return outputDateFormat.format(date)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
            return this
        }

        fun String.toFormattedDay(): String? {
            val dateComponents = this.split("-")
            return if (dateComponents.size == 3) {
                val year = dateComponents[0].toInt()
                val month = dateComponents[1].toInt() - 1
                val day = dateComponents[2].toInt()

                val calendar = Calendar.getInstance()
                calendar.set(year, month, day)
                val outputDateFormat = SimpleDateFormat("EE", Locale.getDefault())
                return outputDateFormat.format(calendar.time)
            } else {
                null
            }
        }

        fun getBGColor(weatherType: WeatherType): Color {
            return when (weatherType) {
                WeatherType.SUNNY -> rainyBg
                WeatherType.WINDLY -> sunnyBg
                WeatherType.RAINY -> rainyBg
                else -> cloudyBg
            }
        }

        fun getBgImage(weatherType: WeatherType): Int {
            return when (weatherType) {
                WeatherType.SUNNY -> R.drawable.forest_sunny
                WeatherType.WINDLY -> R.drawable.forest_cloudy
                WeatherType.RAINY -> R.drawable.forest_rainy
                else -> R.drawable.forest_cloudy
            }
        }

        fun getWeatherIcon(weatherType: WeatherType): Int {
            return when (weatherType) {
                WeatherType.SUNNY -> R.drawable.clear_3x
                WeatherType.WINDLY -> R.drawable.partlysunny_2x
                WeatherType.RAINY -> R.drawable.rain_3x
                else -> R.drawable.partlysunny_2x
            }
        }
    }
}
