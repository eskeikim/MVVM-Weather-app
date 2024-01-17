package com.eskeitec.apps.weatherman.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eskeitec.apps.weatherman.data.datasource.local.converters.WeatherModelConverter
import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity

@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
@TypeConverters(WeatherModelConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}
