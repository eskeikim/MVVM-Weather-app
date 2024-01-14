package com.eskeitec.apps.weatherman.domain.usecase

import com.eskeitec.apps.weatherman.domain.repository.WeatherRepository
import javax.inject.Inject

class ForecastWeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(
        lat: String,
        lon: String,
    ) = weatherRepository.getDayForecast(lat = lat, lon = lon)
}
