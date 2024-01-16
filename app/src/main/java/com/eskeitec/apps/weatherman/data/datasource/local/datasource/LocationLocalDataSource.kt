package com.eskeitec.apps.weatherman.data.datasource.local.datasource

import androidx.lifecycle.LiveData
import com.eskeitec.apps.weatherman.data.datasource.local.LocationDao
import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationLocalDataSource @Inject constructor(private val locationDao: LocationDao) {

    suspend fun insertLocation(location: LocationEntity) = locationDao.insertLocation(location)

    fun getAllLocations(): LiveData<List<LocationEntity>> = locationDao.getAllLocations()

    fun getAllLocationsFlow(): Flow<List<LocationEntity>> = locationDao.getAllLocationsFlow()

    fun getLocation(locationId: String): LiveData<LocationEntity> =
        locationDao.getLocation(locationId)

    fun removeLocation(location: LocationEntity) = locationDao.removeLocation(location)
}
