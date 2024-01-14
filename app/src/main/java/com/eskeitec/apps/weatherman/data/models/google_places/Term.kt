package com.eskeitec.apps.weatherman.data.models.google_places


import com.google.gson.annotations.SerializedName

data class Term(
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("value")
    val value: String?
)