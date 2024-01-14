package com.eskeitec.apps.weatherman.domain.model

import com.eskeitec.apps.weatherman.data.models.current.CurrentWeatherResponse
import com.eskeitec.apps.weatherman.data.models.forecast.ForecastResponse
import com.eskeitec.apps.weatherman.utils.EpochConverter
import com.eskeitec.apps.weatherman.utils.Utils
import com.eskeitec.apps.weatherman.utils.Utils.Companion.HOUR_TIME_FORMAT
import java.util.Calendar
import kotlin.math.roundToInt

data class WeatherModel(
    val id: Long,
    val temp: String,
    val minTemp: String,
    val maxTemp: String,
    val weatherType: WeatherType,
    val city: String?,
    val country: String?,
    val date: String?,
    val time: String?,

)

enum class WeatherType {
    RAINY,
    CLOUDY,
    WINDLY,
    SUNNY,
}

fun CurrentWeatherResponse.toWeatherModel(): WeatherModel {
    val temp = this.main?.temp?.minus(273.15)?.roundToInt()
    val minTemp = this.main?.tempMin?.minus(273.15)?.roundToInt()
    val maxTemp = this.main?.tempMax?.minus(273.15)?.roundToInt()
    return WeatherModel(
        id = this.id?.toLong()!!,
        temp = "$temp",
        minTemp = "$minTemp",
        maxTemp = "$maxTemp",
        weatherType = getWeatherType(this.weather?.first()?.main),
        city = this.name,
        country = this.sys?.country,
        time = Utils.getDateFromTime(
            this.dt ?: Calendar.getInstance().timeInMillis,
            HOUR_TIME_FORMAT,
        ),
        date = EpochConverter.readTimestamp(
            this.dt ?: Calendar.getInstance().timeInMillis,
        ),

    )
}

fun ForecastResponse.toForecastModel(): ForecastModel {
    val weatherList = arrayListOf<WeatherModel>()
    val weather = this.list
    val city = this.city?.name
    val country = this.city?.country
    for (item in weather) {
        val temp = item.main?.temp?.minus(273.15)?.roundToInt()
        val minTemp = item.main?.tempMin?.minus(273.15)?.roundToInt()
        val maxTemp = item.main?.tempMax?.minus(273.15)?.roundToInt()

        val model = WeatherModel(
            id = item.dt?.toLong() ?: -1L,
            temp = "$temp",
            minTemp = "$minTemp",
            maxTemp = "$maxTemp",
            weatherType = getWeatherType(item.weather?.first()?.main),
            city = city,
            country = country,
            time = Utils.getDateFromTime(
                item.dt ?: Calendar.getInstance().timeInMillis,
                HOUR_TIME_FORMAT,
            ),
            date = EpochConverter.readTimestamp(
                item.dt ?: Calendar.getInstance().timeInMillis,
            ),

        )
        weatherList.add(model)
    }
    return ForecastModel(city = "$city $country", weatherModel = weatherList)
}

fun getWeatherType(main: String?): WeatherType {
    return when (main) {
        "Rain" -> WeatherType.RAINY
        "Clouds" -> WeatherType.CLOUDY
        "wind" -> WeatherType.WINDLY
        else -> WeatherType.SUNNY
    }
}
