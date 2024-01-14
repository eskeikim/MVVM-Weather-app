package com.eskeitec.apps.weatherman.domain.model

data class ForecastModel(
    val city: String,
    val weatherModel: List<WeatherModel>,
)
