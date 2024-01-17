package com.eskeitec.apps.weatherman.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eskeitec.apps.weatherman.presentation.Screen
import com.eskeitec.apps.weatherman.presentation.addlocation.AddLocationScreen
import com.eskeitec.apps.weatherman.presentation.addlocation.ViewWeatherScreen
import com.eskeitec.apps.weatherman.presentation.current.CurrentWeatherScreen
import com.eskeitec.apps.weatherman.presentation.current.HomeScreen
import com.eskeitec.apps.weatherman.presentation.favourites.ListFavouritesScreen
import com.eskeitec.apps.weatherman.presentation.shared.SharedViewModel
import com.eskeitec.apps.weatherman.utils.sharedViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun ComposeNavigation(defaultLocation: LatLng) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Home.name) {
        composable(route = Screen.Home.name) { backStackEntry ->
            val sharedViewModel =
                backStackEntry.sharedViewModel<SharedViewModel>(navController)

            HomeScreen(
                navController,
                defaultLocation = defaultLocation,
            )
        }
        composable(
            route = "${Screen.Current.name}/{latlon}",
            arguments = listOf(
                navArgument("latlon") {
                    type =
                        NavType.StringType
                },
            ),
        ) { backStackEntry ->
            val latlon = backStackEntry.arguments?.getString("latlon") ?: ""
            CurrentWeatherScreen(
                currentLocation = latlon,
                navController = navController,
            )
        }
        composable(route = Screen.AddLocation.name) { backStackEntry ->
            val sharedViewModel =
                backStackEntry.sharedViewModel<SharedViewModel>(navController)
            AddLocationScreen(navController)
        }
        composable(
            route = "${Screen.ViewWeatherScreen.name}/{latlon}",
            arguments = listOf(
                navArgument("latlon") {
                    type =
                        NavType.StringType
                },
            ),
        ) { backStackEntry ->
            val latlon = backStackEntry.arguments?.getString("latlon") ?: ""
            ViewWeatherScreen(navController, currentLocation = latlon)
        }
        composable(route = Screen.ListFavouritesScreen.name) { backStackEntry ->
            ListFavouritesScreen(navController)
        }
    }
}
