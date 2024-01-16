package com.eskeitec.apps.weatherman.data.datasource.local.converters

import androidx.room.TypeConverter
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherModelConverter {
    val gson = Gson()

    @TypeConverter
    fun toList(item: String): List<WeatherModel> {
        val type = object : TypeToken<List<WeatherModel>>() {}.type
        return gson.fromJson(item, type)
    }

    @TypeConverter
    fun toString(list: List<WeatherModel>): String {
        return gson.toJson(list)
    }
}
