package com.eskeitec.apps.weatherman.domain.model

import com.eskeitec.apps.weatherman.data.models.current.CurrentWeatherResponse
import kotlin.math.roundToInt

data class WeatherModel(
    val id: Long,
    val temp: String,
    val minTemp: String,
    val maxTemp: String,
    val weatherType: WeatherType,

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

    )
}

fun getWeatherType(main: String?): WeatherType {
    return when (main) {
        "rain" -> WeatherType.RAINY
        "cloud" -> WeatherType.CLOUDY
        "wind" -> WeatherType.WINDLY
        else -> WeatherType.SUNNY
    }
}
