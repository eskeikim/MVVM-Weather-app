package com.eskeitec.apps.weatherman.data.models.google_places

import com.google.gson.annotations.SerializedName

data class GooglePlacesResponse(
    @SerializedName("predictions")
    val predictions: List<Prediction>,
    @SerializedName("status")
    val status: String?,
)
