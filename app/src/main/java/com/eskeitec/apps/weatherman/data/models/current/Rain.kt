package com.eskeitec.apps.weatherman.data.models.current

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val h: Double?,
)
