package com.eskeitec.apps.weatherman.data.datasource.network

import com.eskeitec.apps.weatherman.BuildConfig
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: WeatherApiService) {
    suspend fun getCurrentWeather(
        lat: String,
        lon: String,
    ) = apiService.getCurrentWeather(lat, lon, BuildConfig.OPEN_API_KEY)

    suspend fun getDayForecast(
        lat: String,
        lon: String,
    ) = apiService.getDaysForecast(lat, lon, BuildConfig.OPEN_API_KEY)
}
