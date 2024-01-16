package com.eskeitec.apps.weatherman.data.models.citydetails


import com.google.gson.annotations.SerializedName

data class CityDetailsResponse(
    @SerializedName("result")
    val result: Result?,
    @SerializedName("status")
    val status: String?
)