package com.eskeitec.apps.weatherman.presentation.current

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity
import com.eskeitec.apps.weatherman.domain.usecase.FavouriteLocationUseCase
import com.eskeitec.apps.weatherman.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase,
    private val favouriteLocationUseCase: FavouriteLocationUseCase,
) :
    ViewModel() {
    private var _currentWeatherState =
        MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Loading)
    val currentWeatherState = _currentWeatherState.asStateFlow()

    private var _isFavouriteAdded = MutableLiveData<Boolean>()
    val isFavouriteAdded: LiveData<Boolean>
        get() = _isFavouriteAdded

    fun getCurrentWeatherData(lat: String, lon: String) {
        _currentWeatherState.value = CurrentWeatherState.Loading
        viewModelScope.launch {
            val response = weatherUseCase.invoke(lat, lon)
            when (response) {
                is Resource.Success ->
                    _currentWeatherState.value =
                        CurrentWeatherState.Success(response.data)

                is Resource.Error ->
                    _currentWeatherState.value =
                        CurrentWeatherState.Error(response.message)
            }
        }
    }

    fun addLocationToFavourite(locationEntity: LocationEntity) {
        viewModelScope.launch {
            favouriteLocationUseCase.invoke(locationEntity)
        }
    }

    fun isFavouriteAdded(isAdded: Boolean = true) {
        _isFavouriteAdded.value = isAdded

    }
}
