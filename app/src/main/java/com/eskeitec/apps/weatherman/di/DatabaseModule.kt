package com.eskeitec.apps.weatherman.di

import android.content.Context
import androidx.room.Room
import com.eskeitec.apps.weatherman.data.datasource.local.LocationDao
import com.eskeitec.apps.weatherman.data.datasource.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_db",
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesLocationDao(weatherDatabase: WeatherDatabase): LocationDao {
        return weatherDatabase.locationDao()
    }
}
