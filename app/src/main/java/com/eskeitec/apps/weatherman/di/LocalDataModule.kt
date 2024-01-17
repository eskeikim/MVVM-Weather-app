package com.eskeitec.apps.weatherman.di

import com.eskeitec.apps.weatherman.data.datasource.local.LocationDao
import com.eskeitec.apps.weatherman.data.datasource.local.datasource.LocationLocalDataSource
import com.eskeitec.apps.weatherman.domain.repository.WeatherRepository
import com.eskeitec.apps.weatherman.domain.usecase.FavouriteLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun providesLocationLocalDataSource(locationDao: LocationDao): LocationLocalDataSource =
        LocationLocalDataSource(locationDao)

    @Provides
    @Singleton
    fun providesFavouriteLocationUseCase(weatherRepository: WeatherRepository): FavouriteLocationUseCase =
        FavouriteLocationUseCase(weatherRepository)
}
