package com.eskeitec.apps.weatherman.data.models.citydetails

import com.google.gson.annotations.SerializedName

data class CityDetailsResponse(
    @SerializedName("result")
    val result: CityDetails?,
    @SerializedName("status")
    val status: String?,
)
