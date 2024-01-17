package com.eskeitec.apps.weatherman.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eskeitec.apps.weatherman.domain.model.WeatherModel

@Entity(tableName = "location_entity")
class LocationEntity(
    @PrimaryKey(autoGenerate = false)
    val location_id: Long,
    @ColumnInfo(name = "city_name")
    val cityName: String,
    @ColumnInfo(name = "weather_type")
    val weatherType: String,
    val temp: String,
    @ColumnInfo(name = "min_temp")
    val minTemp: String,
    @ColumnInfo(name = "max_temp")
    val maxTemp: String,
    @ColumnInfo(name = "days_forecast")
    val daysForecast: List<WeatherModel>,
    val latlog: String,
    val time: String,
    val date: String,
)
