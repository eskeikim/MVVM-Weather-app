package com.eskeitec.apps.weatherman.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.eskeitec.apps.weatherman.data.datasource.location.LocationService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationService: LocationService,
) {
    @RequiresApi(Build.VERSION_CODES.S)
    operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()
}
