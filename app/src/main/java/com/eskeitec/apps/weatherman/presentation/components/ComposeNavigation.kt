package com.eskeitec.apps.weatherman.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eskeitec.apps.weatherman.presentation.Screen
import com.eskeitec.apps.weatherman.presentation.addlocation.AddLocationScreen
import com.eskeitec.apps.weatherman.presentation.current.CurrentWeatherScreen
import com.google.android.gms.maps.model.LatLng

@Composable
fun ComposeNavigation(currentLoc: LatLng) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Current.name) {
        composable(route = Screen.Current.name) {
            CurrentWeatherScreen(navController, currentLoc = currentLoc)
        }
        composable(route = Screen.AddLocation.name) {
            AddLocationScreen(navController)
        }
    }
}
