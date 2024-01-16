package com.eskeitec.apps.weatherman.presentation.current

import com.eskeitec.apps.weatherman.data.models.citydetails.CityDetails
import com.eskeitec.apps.weatherman.data.models.google_places.Prediction
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.google.android.gms.maps.model.LatLng

sealed interface CurrentWeatherState {
    data class Success(val data: WeatherModel?) : CurrentWeatherState
    data class Error(val error: String?) : CurrentWeatherState
    object Loading : CurrentWeatherState
}

sealed interface CityDetailsState {
    data class Success(val data: CityDetails?) : CityDetailsState
    data class Error(val error: String?) : CityDetailsState
    object Loading : CityDetailsState
    object InitialState : CityDetailsState
}

sealed interface PlacesState {
    data class Success(val data: List<Prediction>?) : PlacesState
    data class Error(val error: String?) : PlacesState
    object Loading : PlacesState
    object InitialState : PlacesState
}

sealed interface CurrentLocationState {
    data class Selected(val location: LatLng?) : CurrentLocationState
    data class Default(val location: LatLng?) : CurrentLocationState
    object InitialState : CurrentLocationState
}
