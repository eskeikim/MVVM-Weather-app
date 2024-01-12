package com.eskeitec.apps.weatherman.domain.repository

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.models.current.CurrentWeatherResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: String, lon: String): Resource<CurrentWeatherResponse>
}
