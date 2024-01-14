package com.eskeitec.apps.weatherman.data.datasource.network

import com.eskeitec.apps.weatherman.data.models.current.CurrentWeatherResponse
import com.eskeitec.apps.weatherman.data.models.forecast.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appKey: String,
        @Query("units") units: String = "metric",
    ): Response<CurrentWeatherResponse>

    @GET("forecast")
    suspend fun getDaysForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appKey: String,
        @Query("units") units: String = "metric",
    ): Response<ForecastResponse>

}
