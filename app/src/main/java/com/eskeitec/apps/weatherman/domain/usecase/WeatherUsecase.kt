package com.eskeitec.apps.weatherman.domain.usecase

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.models.current.CurrentWeatherResponse
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.eskeitec.apps.weatherman.domain.repository.WeatherRepository
import javax.inject.Inject

interface WeatherUseCase {
    suspend fun getCurrentWeather(lat: String, lon: String): Resource<WeatherModel>
}

class WeatherUseCaseImpl @Inject constructor(private val weatherRepository: WeatherRepository) :
    WeatherUseCase {
    override suspend fun getCurrentWeather(
        lat: String,
        lon: String,
    ): Resource<WeatherModel> {
        return weatherRepository.getCurrentWeather(lat = lat, lon = lon)
    }
}
