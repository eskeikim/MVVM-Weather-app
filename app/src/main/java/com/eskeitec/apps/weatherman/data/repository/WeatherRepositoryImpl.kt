package com.eskeitec.apps.weatherman.data.repository

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.datasource.network.WeatherApiService
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.eskeitec.apps.weatherman.domain.model.toWeatherModel
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
    ): Resource<WeatherModel> {
        val response = weatherApiService.getCurrentWeather(lat, lon, API_KEY)

        return try {
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()
                val cWeather = data?.toWeatherModel()
                println("Current Weather == $cWeather")
                Resource.success(cWeather)
            } else {
                Resource.error(null, Constants.DEFAULT_ERROR)
            }
        } catch (exception: Exception) {
            Resource.error(null, Constants.DEFAULT_ERROR)
        }
    }
}
