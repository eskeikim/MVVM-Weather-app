package com.eskeitec.apps.weatherman.presentation.current

import com.eskeitec.apps.weatherman.domain.model.WeatherModel

sealed interface CurrentWeatherState {
    data class Success(val data: WeatherModel?) : CurrentWeatherState
    data class Error(val error: String?) : CurrentWeatherState
    object Loading : CurrentWeatherState
}
