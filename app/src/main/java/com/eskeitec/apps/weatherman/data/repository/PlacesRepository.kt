package com.eskeitec.apps.weatherman.data.repository

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.models.google_places.Prediction

interface PlacesRepository {
    suspend fun getPlaces(input: String): Resource<List<Prediction>>
}
