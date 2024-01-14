package com.eskeitec.apps.weatherman.data.datasource.network

import com.eskeitec.apps.weatherman.BuildConfig
import com.eskeitec.apps.weatherman.data.models.google_places.GooglePlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApi {
    @GET("maps/api/place/autocomplete/json")
    suspend fun getPlaces(
        @Query("key") key: String = BuildConfig.MAPS_API_KEY,
        @Query("type") types: String = "address",
        @Query("input") input: String,
    ): GooglePlacesResponse
}
