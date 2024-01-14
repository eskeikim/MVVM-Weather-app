package com.eskeitec.apps.weatherman.data.models.forecast


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int?
)