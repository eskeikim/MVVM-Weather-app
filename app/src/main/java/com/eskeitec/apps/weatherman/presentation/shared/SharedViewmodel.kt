package com.eskeitec.apps.weatherman.presentation.shared

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eskeitec.apps.weatherman.presentation.current.CurrentLocationState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {

    private var _defaultLocation = MutableLiveData<LatLng>()
    val defaultLocation = _defaultLocation

    private var _currentLocation =
        MutableStateFlow<CurrentLocationState>(CurrentLocationState.InitialState)
    val currentLocation = _currentLocation.asStateFlow()

    fun updateLocation(newLocation: LatLng, isDefault: Boolean = false) {
        _currentLocation.value = CurrentLocationState.Selected(newLocation)
        println("LOC VM Upd $newLocation")
        if (isDefault) _defaultLocation.value = newLocation
    }

    fun resetLocation() {
        _currentLocation.value = CurrentLocationState.Selected(_defaultLocation.value)
    }
}
