package com.eskeitec.apps.weatherman.domain.usecase

import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity
import com.eskeitec.apps.weatherman.domain.repository.WeatherRepository
import javax.inject.Inject

class FavouriteLocationUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun invoke(locationEntity: LocationEntity) =
        weatherRepository.insertFavouriteLocation(locationEntity)

    suspend fun removeLocation(locationEntity: LocationEntity) =
        weatherRepository.removeLocation(locationEntity)
}
