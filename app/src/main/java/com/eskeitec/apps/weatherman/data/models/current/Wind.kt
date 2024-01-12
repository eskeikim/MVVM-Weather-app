package com.eskeitec.apps.weatherman.data.models.current

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("speed")
    val speed: Any?,
)
