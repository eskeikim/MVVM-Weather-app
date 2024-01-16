package com.eskeitec.apps.weatherman.domain.usecase

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.models.google_places.Prediction
import com.eskeitec.apps.weatherman.data.repository.PlacesRepository
import javax.inject.Inject

class AddLocationUseCase @Inject constructor(private val placesRepository: PlacesRepository) {
    suspend fun invoke(address: String) {
        val response = placesRepository.getPlaces(address)
        var list = emptyList<Prediction>()
        when (response) {
            is Resource.Success -> {
                println("Loaded VM data ${response.data?.size} :: ${response.data?.first()}")
                list = response.data ?: emptyList()
            }

            else -> list=emptyList()
        }
    }
}
