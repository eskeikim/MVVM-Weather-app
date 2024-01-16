package com.eskeitec.apps.weatherman.data.models.citydetails

import com.google.gson.annotations.SerializedName

data class CityDetails(
    @SerializedName("geometry")
    val geometry: Geometry?,
    @SerializedName("name")
    val name: String?,
)
