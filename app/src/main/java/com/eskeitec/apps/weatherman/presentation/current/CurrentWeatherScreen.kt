package com.eskeitec.apps.weatherman.presentation.current

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import timber.log.Timber

@Composable
fun CurrentWeatherScreen(
    navController: NavHostController,
    weatherViewModel: CurrentWeatherViewModel = hiltViewModel(),
) {
    println("Viewmodel init>>>")
    Timber.d("Viewmodel init>>>")
}
