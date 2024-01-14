package com.eskeitec.apps.weatherman.data.repository

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.datasource.network.PlacesApi
import com.eskeitec.apps.weatherman.data.models.google_places.Prediction
import com.eskeitec.apps.weatherman.utils.Constants
import java.lang.Exception
import javax.inject.Inject

class PlacesRepository @Inject constructor(private val api: PlacesApi) {
    suspend fun getPlaces(input: String): Resource<List<Prediction>> {
        val response = api.getPlaces(input = input)
        try {
            if (response.predictions == null) {
                return Resource.Error(Constants.DEFAULT_ERROR)
            } else {
                Resource.Success(response.predictions)
            }
        } catch (e: Exception) {
            return Resource.Error("Failed to get location places")
        }
    }
}
