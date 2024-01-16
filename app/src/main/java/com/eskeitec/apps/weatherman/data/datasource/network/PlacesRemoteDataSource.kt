package com.eskeitec.apps.weatherman.data.datasource.network

import javax.inject.Inject

class PlacesRemoteDataSource @Inject constructor(private val placesApi: PlacesApi) {
    suspend fun getPlaces(input: String) = placesApi.getPlaces(input = input)
    suspend fun getCityDetails(placeId: String) = placesApi.getCityDetails(placeId = placeId)
}
