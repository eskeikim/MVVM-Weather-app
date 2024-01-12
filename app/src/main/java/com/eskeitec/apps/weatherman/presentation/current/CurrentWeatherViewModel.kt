package com.eskeitec.apps.weatherman.presentation.current

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.eskeitec.apps.weatherman.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase) :
    ViewModel() {
    private var _currentWeather = MutableLiveData<WeatherModel?>()
    val currentWeather: LiveData<WeatherModel?>
        get() = _currentWeather

    init {
        val lat = "-1.286389"
        val lon = "36.817223"
        getCurrentWeatherData(lat, lon)
    }

    private fun getCurrentWeatherData(lat: String, lon: String) {
        viewModelScope.launch {
            val response = weatherUseCase.getCurrentWeather(lat, lon)
            _currentWeather.value = response.data
        }
    }
}
