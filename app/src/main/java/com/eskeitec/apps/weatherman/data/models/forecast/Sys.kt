package com.eskeitec.apps.weatherman.data.models.forecast


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    val pod: String?
)