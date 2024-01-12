package com.eskeitec.apps.weatherman.domain.model

import com.eskeitec.apps.weatherman.data.models.current.CurrentWeatherResponse

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
    return WeatherModel(
        id = this.id?.toLong()!!,
        temp = "${this.main?.temp}",
        minTemp = "${this.main?.tempMin}",
        maxTemp = "${this.main?.tempMax}",
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
