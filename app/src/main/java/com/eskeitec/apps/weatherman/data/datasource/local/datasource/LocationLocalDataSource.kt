package com.eskeitec.apps.weatherman.data.datasource.local.datasource

import androidx.lifecycle.LiveData
import com.eskeitec.apps.weatherman.data.datasource.local.LocationDao
import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationLocalDataSource @Inject constructor(private val locationDao: LocationDao) {

    suspend fun insertLocation(location: LocationEntity) = locationDao.insertLocation(location)

    fun getAllLocationsL(): LiveData<List<LocationEntity>> = locationDao.getAllLocationsL()
    suspend fun getAllLocations(): List<LocationEntity> = locationDao.getAllLocations()

    fun getAllLocationsFlow(): Flow<List<LocationEntity>> = locationDao.getAllLocationsFlow()

    fun getLocation(locationId: Long): LiveData<LocationEntity> =
        locationDao.getLocation(locationId.toInt())

    suspend fun removeLocation(location: LocationEntity) = locationDao.removeLocation(location)
}
