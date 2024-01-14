package com.eskeitec.apps.weatherman.domain.repository

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.domain.model.ForecastModel
import com.eskeitec.apps.weatherman.domain.model.WeatherModel

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: String, lon: String): Resource<WeatherModel>
    suspend fun getDayForecast(lat: String, lon: String): Resource<ForecastModel>
}
