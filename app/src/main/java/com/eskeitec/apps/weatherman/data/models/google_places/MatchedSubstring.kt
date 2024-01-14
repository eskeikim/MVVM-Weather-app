package com.eskeitec.apps.weatherman.data.models.google_places


import com.google.gson.annotations.SerializedName

data class MatchedSubstring(
    @SerializedName("length")
    val length: Int?,
    @SerializedName("offset")
    val offset: Int?
)