package com.eskeitec.apps.weatherman.data.models.forecast

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val list: List<Forecast>,
    @SerializedName("message")
    val message: Int?,
)
