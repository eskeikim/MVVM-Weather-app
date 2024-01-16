package com.eskeitec.apps.weatherman.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Query("SELECT * FROM location_entity")
    fun getAllLocationsL(): LiveData<List<LocationEntity>>

    @Query("SELECT * FROM location_entity")
    suspend fun getAllLocations(): List<LocationEntity>

    @Query("SELECT * FROM location_entity")
    fun getAllLocationsFlow(): Flow<List<LocationEntity>>

    @Query("SELECT * FROM location_entity WHERE location_id=:locationId")
    fun getLocation(locationId: String): LiveData<LocationEntity>

    @Delete
    fun removeLocation(location: LocationEntity)
}
