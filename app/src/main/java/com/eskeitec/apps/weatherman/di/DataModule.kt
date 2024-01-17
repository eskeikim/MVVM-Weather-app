package com.eskeitec.apps.weatherman.di

import com.eskeitec.apps.weatherman.data.datasource.local.datasource.LocationLocalDataSource
import com.eskeitec.apps.weatherman.data.datasource.network.PlacesApi
import com.eskeitec.apps.weatherman.data.datasource.network.PlacesRemoteDataSource
import com.eskeitec.apps.weatherman.data.datasource.network.RemoteDataSource
import com.eskeitec.apps.weatherman.data.datasource.network.WeatherApiService
import com.eskeitec.apps.weatherman.data.repository.PlacesRepository
import com.eskeitec.apps.weatherman.data.repository.PlacesRepositoryImpl
import com.eskeitec.apps.weatherman.data.repository.WeatherRepositoryImpl
import com.eskeitec.apps.weatherman.domain.repository.WeatherRepository
import com.eskeitec.apps.weatherman.domain.usecase.CityDetailsUseCase
import com.eskeitec.apps.weatherman.domain.usecase.ForecastWeatherUseCase
import com.eskeitec.apps.weatherman.domain.usecase.PlacesUseCase
import com.eskeitec.apps.weatherman.domain.usecase.WeatherUseCase
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
    fun providesWeatherRemoteDataSource(apiService: WeatherApiService): RemoteDataSource =
        RemoteDataSource(apiService)

    @Provides
    @Singleton
    fun providesPlacesRemoteDataSource(placesApi: PlacesApi): PlacesRemoteDataSource =
        PlacesRemoteDataSource(placesApi = placesApi)

    @Provides
    @Singleton
    fun providesWeatherRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocationLocalDataSource,
    ): WeatherRepository =
        WeatherRepositoryImpl(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun providesPlacesRepository(placesRemoteDataSource: PlacesRemoteDataSource): PlacesRepository =
        PlacesRepositoryImpl(placesRemoteDataSource)

    @Provides
    @Singleton
    fun providesWeatherUseCase(weatherRepository: WeatherRepository): WeatherUseCase =
        WeatherUseCase(weatherRepository)

    @Provides
    @Singleton
    fun providesForecastWeatherUseCase(weatherRepository: WeatherRepository): ForecastWeatherUseCase =
        ForecastWeatherUseCase(weatherRepository)

    @Provides
    @Singleton
    fun providesPlacesUseCase(placesRepository: PlacesRepository): PlacesUseCase =
        PlacesUseCase(placesRepository)

    @Provides
    @Singleton
    fun providesCityDetailsUseCase(placesRepository: PlacesRepository): CityDetailsUseCase =
        CityDetailsUseCase(placesRepository)
}
