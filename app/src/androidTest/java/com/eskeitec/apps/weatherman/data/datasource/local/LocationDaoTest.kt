package com.eskeitec.apps.weatherman.data.datasource.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity
import com.eskeitec.apps.weatherman.domain.model.WeatherType
import com.eskeitec.apps.weatherman.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class LocationDaoTest {
    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var weatherDatabase: WeatherDatabase
    private lateinit var locationDao: LocationDao

    @Before
    fun setUp() {
        weatherDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java,
        ).allowMainThreadQueries().build()

        locationDao = weatherDatabase.locationDao()
    }

    @After
    fun tearDown() {
        weatherDatabase.close()
    }

    @Test
    fun addLocationAsFavourite() = runBlocking {
        val locationEntity = LocationEntity(
            location_id = 123,
            cityName = "Embu",
            date = "Oct 27",
            time = "08:00 Am",
            temp = "28º",
            maxTemp = "29º",
            minTemp = "27º",
            weatherType = WeatherType.RAINY.name,
            latlog = "{\"latitude\":45.5152,\"longitude\":-122.6784}",
            daysForecast = emptyList(),
        )

        locationDao.insertLocation(locationEntity)
        val location = locationDao.getLocation(123).getOrAwaitValue()
        assertThat(location.location_id).isEqualTo(locationEntity.location_id)
    }

    @Test
    fun removeLocationFromFavourite() = runBlocking {
        val locationEntity = LocationEntity(
            location_id = 123,
            cityName = "Embu",
            date = "Oct 27",
            time = "08:00 Am",
            temp = "28º",
            maxTemp = "29º",
            minTemp = "27º",
            weatherType = WeatherType.RAINY.name,
            latlog = "{\"latitude\":45.5152,\"longitude\":-122.6784}",
            daysForecast = emptyList(),
        )
        locationDao.insertLocation(locationEntity)
        locationDao.removeLocation(locationEntity)
        val location = locationDao.getLocation(123).getOrAwaitValue()
        assertThat(location).isNull()
    }
}
