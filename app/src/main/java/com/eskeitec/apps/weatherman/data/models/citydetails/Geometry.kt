package com.eskeitec.apps.weatherman.data.models.citydetails

import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("location")
    val location: GeoLocation?,
)
