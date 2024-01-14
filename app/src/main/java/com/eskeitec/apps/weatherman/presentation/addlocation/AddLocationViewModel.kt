package com.eskeitec.apps.weatherman.presentation.add_location

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eskeitec.apps.weatherman.common.Resource
import com.eskeitec.apps.weatherman.data.models.google_places.Prediction
import com.eskeitec.apps.weatherman.data.repository.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddLocationViewModel @Inject constructor(private val placesRepository: PlacesRepository) :
    ViewModel() {
    val loading = mutableStateOf(false)
    val places = mutableStateOf(ArrayList<Prediction>())

    fun getPlaces(address: String) {
        viewModelScope.launch {
            loading.value = true
            val response = placesRepository.getPlaces(address)
            when (response) {
                is Resource.Success -> {
                    places.value = ArrayList(response.data!!)
                }

                else -> {}
            }
            loading.value = false
        }
    }
}
