package com.eskeitec.apps.weatherman.data.models.citydetails


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lng")
    val lng: Double?
)