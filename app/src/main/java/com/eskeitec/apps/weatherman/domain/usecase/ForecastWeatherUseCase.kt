package com.eskeitec.apps.weatherman.domain.usecase

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.domain.model.ForecastModel
import com.eskeitec.apps.weatherman.domain.repository.WeatherRepository
import javax.inject.Inject

interface ForecastWeatherUseCase {
    suspend fun getDayForecast(lat: String, lon: String): Resource<ForecastModel>
}

class ForecastWeatherUseCaseImpl @Inject constructor(private val weatherRepository: WeatherRepository) :
    ForecastWeatherUseCase {

    override suspend fun getDayForecast(lat: String, lon: String): Resource<ForecastModel> {
        return weatherRepository.getDayForecast(lat = lat, lon = lon)
    }
}
