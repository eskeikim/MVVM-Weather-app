package com.eskeitec.apps.weatherman.presentation.daysforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eskeitec.apps.weatherman.domain.model.ForecastModel
import com.eskeitec.apps.weatherman.domain.usecase.ForecastWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastWeatherViewModel @Inject constructor(private val weatherUseCase: ForecastWeatherUseCase) :
    ViewModel() {
    private var _forecastWeather = MutableLiveData<ForecastModel?>()
    val forecastWeather: LiveData<ForecastModel?>
        get() = _forecastWeather

    fun getForecastWeatherData(lat: String, lon: String) {
        viewModelScope.launch {
            val response = weatherUseCase.invoke(lat, lon)
            _forecastWeather.value = response.data
        }
    }
}
