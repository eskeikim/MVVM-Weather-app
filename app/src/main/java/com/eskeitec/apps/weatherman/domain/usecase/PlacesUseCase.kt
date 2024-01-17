package com.eskeitec.apps.weatherman.domain.usecase

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.models.google_places.Prediction
import com.eskeitec.apps.weatherman.data.repository.PlacesRepository
import timber.log.Timber
import javax.inject.Inject

class PlacesUseCase @Inject constructor(private val placesRepository: PlacesRepository) {
    suspend fun invoke(address: String): List<Prediction> {
        val list: List<Prediction> = when (val response = placesRepository.getPlaces(address)) {
            is Resource.Success -> {
                Timber.d("Loaded VM data ${response.data?.size}}")
                response.data ?: emptyList()
            }

            else -> emptyList()
        }
        return list
    }
}
