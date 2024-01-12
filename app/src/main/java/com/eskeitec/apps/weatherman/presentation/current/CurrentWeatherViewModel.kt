package com.eskeitec.apps.weatherman.presentation.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eskeitec.apps.weatherman.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase) :
    ViewModel() {
    init {
        val lat = "-1.286389"
        val lon = "36.817223"
        getCurretWeatherData(lat, lon)
    }

    private fun getCurretWeatherData(lat: String, lon: String) {
        viewModelScope.launch {
            weatherUseCase.getCurrentWeather(lat, lon)
        }
    }
}
