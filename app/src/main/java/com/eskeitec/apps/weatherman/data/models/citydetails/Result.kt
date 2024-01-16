package com.eskeitec.apps.weatherman.data.models.citydetails


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("geometry")
    val geometry: Geometry?,
    @SerializedName("name")
    val name: String?
)