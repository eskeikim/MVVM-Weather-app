package com.eskeitec.apps.weatherman.data.repository

import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.datasource.local.datasource.LocationLocalDataSource
import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity
import com.eskeitec.apps.weatherman.data.datasource.network.RemoteDataSource
import com.eskeitec.apps.weatherman.domain.model.ForecastModel
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.eskeitec.apps.weatherman.domain.model.toForecastModel
import com.eskeitec.apps.weatherman.domain.model.toWeatherModel
import com.eskeitec.apps.weatherman.domain.repository.WeatherRepository
import com.eskeitec.apps.weatherman.utils.Constants
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocationLocalDataSource,
) :
    WeatherRepository {
    override suspend fun getCurrentWeather(
        lat: String,
        lon: String,
    ): Resource<WeatherModel> {
        val response = remoteDataSource.getCurrentWeather(lat, lon)

        return try {
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()
                val cWeather = data?.toWeatherModel()
                println("Current Weather == $cWeather")
                Resource.Success(cWeather)
            } else {
                Resource.Error(Constants.DEFAULT_ERROR)
            }
        } catch (exception: Exception) {
            Resource.Error(Constants.DEFAULT_ERROR)
        }
    }

    override suspend fun getDayForecast(lat: String, lon: String): Resource<ForecastModel> {
        val response = remoteDataSource.getDayForecast(lat, lon)

        return try {
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()
                val cWeather = data?.toForecastModel()
                println("Current Weather == $cWeather")
                Resource.Success(cWeather)
            } else {
                Resource.Error(Constants.DEFAULT_ERROR)
            }
        } catch (exception: Exception) {
            Resource.Error(Constants.DEFAULT_ERROR)
        }
    }

    override suspend fun insertFavouriteLocation(locationEntity: LocationEntity) {
        localDataSource.insertLocation(locationEntity)
    }
}
