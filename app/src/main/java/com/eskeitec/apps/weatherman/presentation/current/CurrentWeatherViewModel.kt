package com.eskeitec.apps.weatherman.presentation.current

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.eskeitec.apps.weatherman.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase) :
    ViewModel() {
    private var _currentWeatherState =
        MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Loading)
    val currentWeatherState = _currentWeatherState.asStateFlow()

    private var _currentWeather = MutableLiveData<WeatherModel?>()
    val currentWeather: LiveData<WeatherModel?>
        get() = _currentWeather

    init {
        val lat = "-1.286389"
        val lon = "36.817223"
        getCurrentWeatherData(lat, lon)
    }

    private fun getCurrentWeatherData(lat: String, lon: String) {
        _currentWeatherState.value = CurrentWeatherState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = weatherUseCase.getCurrentWeather(lat, lon)
            when (response) {
                is Resource.Success ->
                    _currentWeatherState.value =
                        CurrentWeatherState.Success(response.data)

                is Resource.Error ->
                    _currentWeatherState.value =
                        CurrentWeatherState.Error(response.message)
            }
//            _currentWeather.value = response.data
        }
    }
}
