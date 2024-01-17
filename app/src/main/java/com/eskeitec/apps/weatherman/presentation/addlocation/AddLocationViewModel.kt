package com.eskeitec.apps.weatherman.presentation.addlocation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eskeitec.apps.weatherman.data.models.google_places.Prediction
import com.eskeitec.apps.weatherman.domain.usecase.CityDetailsUseCase
import com.eskeitec.apps.weatherman.domain.usecase.PlacesUseCase
import com.eskeitec.apps.weatherman.presentation.current.CityDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddLocationViewModel @Inject constructor(
    private val placesUsecase: PlacesUseCase,
    private val cityDetailsUseCase: CityDetailsUseCase,
) :
    ViewModel() {
    val loading = mutableStateOf(false)
    private val _cityDetails = MutableStateFlow<CityDetailsState>(CityDetailsState.InitialState)
    val cityDetails = _cityDetails.asStateFlow()

    var searchQuery by mutableStateOf("")
        private set

    @OptIn(ExperimentalCoroutinesApi::class)
    val locationPlaces: StateFlow<List<Prediction>> =
        snapshotFlow { searchQuery }
            .mapLatest {
                placesUsecase.invoke(searchQuery)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(15_000),
                initialValue = emptyList(),
            )

    fun onPlaceValueChanged(value: String) {
        searchQuery = value
        if (searchQuery != value) {
            println("SEARCH >>> $value")
            searchQuery = value
        } else {
            println("SEARCH >>> FINAL$value")
        }
    }

    fun onPlaceClick(value: String) {
        _cityDetails.value = CityDetailsState.Loading
        viewModelScope.launch {
            val cityDetails = cityDetailsUseCase.invoke(value)
            if (cityDetails != null) _cityDetails.value = CityDetailsState.Success(cityDetails)
        }
    }
}
