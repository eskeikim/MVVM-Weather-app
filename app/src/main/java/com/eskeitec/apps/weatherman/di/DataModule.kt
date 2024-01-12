package com.eskeitec.apps.weatherman.di

import com.eskeitec.apps.weatherman.data.datasource.network.WeatherApiService
import com.eskeitec.apps.weatherman.data.repository.WeatherRepositoryImpl
import com.eskeitec.apps.weatherman.domain.repository.WeatherRepository
import com.eskeitec.apps.weatherman.domain.usecase.WeatherUseCase
import com.eskeitec.apps.weatherman.domain.usecase.WeatherUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesWeatherRepository(apiService: WeatherApiService): WeatherRepository =
        WeatherRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun providesWeatherUseCase(weatherRepository: WeatherRepository): WeatherUseCase =
        WeatherUseCaseImpl(weatherRepository)
}
