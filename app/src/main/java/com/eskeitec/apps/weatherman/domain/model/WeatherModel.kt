package com.eskeitec.apps.weatherman.domain.model

import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity
import com.eskeitec.apps.weatherman.data.models.current.CurrentWeatherResponse
import com.eskeitec.apps.weatherman.data.models.forecast.ForecastResponse
import com.eskeitec.apps.weatherman.utils.EpochConverter
import com.eskeitec.apps.weatherman.utils.Utils
import com.eskeitec.apps.weatherman.utils.Utils.Companion.HOUR_TIME_FORMAT
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import java.util.Calendar

data class WeatherModel(
    val id: Long,
    val temp: String,
    val minTemp: String,
    val maxTemp: String,
    val weatherType: WeatherType,
    val city: String,
    val country: String,
    val date: String?,
    val time: String?,
    val latLng: String,

)

enum class WeatherType {
    RAINY,
    CLOUDY,
    WINDLY,
    SUNNY,
}

fun CurrentWeatherResponse.toWeatherModel(): WeatherModel {
    val temp = this.main?.temp?.toInt()
    val minTemp = this.main?.tempMin?.toInt()
    val maxTemp = this.main?.tempMax?.toInt()
    val latLng = Gson().toJson(LatLng(this.coord?.lat ?: 0.0, this.coord?.lon ?: 0.0))
    return WeatherModel(
        id = this.id?.toLong()!!,
        temp = "$temp",
        minTemp = "$minTemp",
        maxTemp = "$maxTemp",
        weatherType = getWeatherType(this.weather?.first()?.main),
        city = "${this.name}",
        country = "${this.sys?.country}",
        time = Utils.getDateFromTime(
            this.dt ?: Calendar.getInstance().timeInMillis,
            HOUR_TIME_FORMAT,
        ),
        date = EpochConverter.readTimestamp(
            this.dt ?: Calendar.getInstance().timeInMillis,
        ),
        latLng = latLng,

    )
}

fun WeatherModel.toLocationEntity(): LocationEntity {
    return LocationEntity(
        location_id = this.id,
        cityName = this.city,
        weatherType = this.weatherType.name,
        temp = this.temp,
        minTemp = this.minTemp,
        maxTemp = this.maxTemp,
        time = "${this.time}",
        date = "${this.date}",
        daysForecast = emptyList(),
        latlog = this.latLng,
    )
}

fun ForecastResponse.toForecastModel(): ForecastModel {
    val weatherList = arrayListOf<WeatherModel>()
    val weather = this.list
    val city = this.city?.name
    val country = this.city?.country
    for (item in weather) {
        val temp = item.main?.temp?.toInt()
        val minTemp = item.main?.tempMin?.toInt()
        val maxTemp = item.main?.tempMax?.toInt()
        val latLng =
            Gson().toJson(LatLng(this.city?.coord?.lat ?: 0.0, this.city?.coord?.lon ?: 0.0))

        val model = WeatherModel(
            id = item.dt ?: -1L,
            temp = "$temp",
            minTemp = "$minTemp",
            maxTemp = "$maxTemp",
            weatherType = getWeatherType(item.weather?.first()?.main),
            city = "$city",
            country = "$country",
            time = Utils.getDateFromTime(
                item.dt ?: Calendar.getInstance().timeInMillis,
                HOUR_TIME_FORMAT,
            ),
            date = EpochConverter.readTimestamp(
                item.dt ?: Calendar.getInstance().timeInMillis,
            ),
            latLng = latLng,

        )
        weatherList.add(model)
    }
    return ForecastModel(city = "$city $country", weatherModel = weatherList)
}

fun getWeatherType(main: String?): WeatherType {
    return when (main) {
        "Rain" -> WeatherType.RAINY
        "Clouds" -> WeatherType.CLOUDY
        else -> WeatherType.SUNNY
    }
}
