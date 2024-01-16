package com.eskeitec.apps.weatherman.data.repository

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.datasource.network.PlacesRemoteDataSource
import com.eskeitec.apps.weatherman.data.models.citydetails.CityDetails
import com.eskeitec.apps.weatherman.data.models.google_places.Prediction
import com.eskeitec.apps.weatherman.utils.Constants
import java.lang.Exception
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(private val placesRemoteDataSource: PlacesRemoteDataSource) :
    PlacesRepository {
    override suspend fun getPlaces(input: String): Resource<List<Prediction>> {
        val response = placesRemoteDataSource.getPlaces(input = input)
        return try {
            println("Loaded Repos data ${response.status}")
            if (response.predictions == null) {
                Resource.Error(Constants.DEFAULT_ERROR)
            } else {
                println("Loaded Repos data ${response.predictions.size} :: ${response.predictions.first()}")
                Resource.Success(response.predictions)
            }
        } catch (e: Exception) {
            Resource.Error("Failed to get location places")
        }
    }

    override suspend fun getCityDetails(placeId: String): Resource<CityDetails> {
        val response = placesRemoteDataSource.getCityDetails(placeId = placeId)
        return try {
            println("Loaded Repos data ${response.status}")
            if (response.result == null) {
                Resource.Error(Constants.DEFAULT_ERROR)
            } else {
                println("Loaded Repos data ${response.result}}")
                Resource.Success(response.result)
            }
        } catch (e: Exception) {
            Resource.Error("Failed to get city details")
        }
    }
}
