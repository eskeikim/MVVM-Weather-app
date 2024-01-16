package com.eskeitec.apps.weatherman.presentation.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity
import com.eskeitec.apps.weatherman.domain.usecase.FavouriteLocationUseCase
import com.eskeitec.apps.weatherman.domain.usecase.GetFavouriteLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouriteLocationUseCase: FavouriteLocationUseCase,
    private val getFavouriteLocationsUseCase: GetFavouriteLocationsUseCase,
) :
    ViewModel() {

    private var _favourites = MutableLiveData<List<LocationEntity>>()
    val favourites: LiveData<List<LocationEntity>>
        get() = _favourites

    init {
        getAllLocations()
    }

    private fun getAllLocations() {
        viewModelScope.launch {
            val response = getFavouriteLocationsUseCase.invoke()
            println("response local fav ${response?.size}")
            _favourites.value = response
        }
    }

    fun addLocationToFavourite(locationEntity: LocationEntity) {
        viewModelScope.launch {
            favouriteLocationUseCase.invoke(locationEntity)
        }
    }
}
