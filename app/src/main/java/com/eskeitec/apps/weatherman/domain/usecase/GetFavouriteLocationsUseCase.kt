package com.eskeitec.apps.weatherman.domain.usecase

import com.eskeitec.apps.weatherman.domain.repository.WeatherRepository
import javax.inject.Inject

class GetFavouriteLocationsUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun invoke() = weatherRepository.getAllFavouriteLocations()
    fun getFavourites() = weatherRepository.getAllFavouriteLocationsL()
}
