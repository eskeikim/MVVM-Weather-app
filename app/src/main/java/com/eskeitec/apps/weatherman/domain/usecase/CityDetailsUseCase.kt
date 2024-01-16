package com.eskeitec.apps.weatherman.domain.usecase

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.models.citydetails.CityDetails
import com.eskeitec.apps.weatherman.data.repository.PlacesRepository
import javax.inject.Inject

class CityDetailsUseCase @Inject constructor(private val placesRepository: PlacesRepository) {
    suspend fun invoke(address: String): CityDetails? {
        val details: CityDetails? = when (val response = placesRepository.getCityDetails(address)) {
            is Resource.Success -> {
                println("Loaded details data ${response.data}")
                response.data
            }

            else -> null
        }
        return details
    }
}
