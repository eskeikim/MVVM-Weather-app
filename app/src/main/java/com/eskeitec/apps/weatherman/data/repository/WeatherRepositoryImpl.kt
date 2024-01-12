package com.eskeitec.apps.weatherman.data.repository

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.datasource.network.WeatherApiService
import com.eskeitec.apps.weatherman.data.models.current.CurrentWeatherResponse
import com.eskeitec.apps.weatherman.domain.repository.WeatherRepository
import com.eskeitec.apps.weatherman.utils.Constants
import com.eskeitec.apps.weatherman.utils.Constants.API_KEY
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherApiService: WeatherApiService) :
    WeatherRepository {
    override suspend fun getCurrentWeather(
        lat: String,
        lon: String,
    ): Resource<CurrentWeatherResponse> {
        val response = weatherApiService.getCurrentWeather(lat, lon, API_KEY)

        return try {
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()

                Resource.success(data)
            } else {
                Resource.error(null, Constants.DEFAULT_ERROR)
            }
        } catch (exception: Exception) {
            Resource.error(null, Constants.DEFAULT_ERROR)
        }
    }
}
