package com.eskeitec.apps.weatherman.presentation.current

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.eskeitec.apps.weatherman.presentation.Screen
import com.eskeitec.apps.weatherman.presentation.shared.SharedViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    defaultLocation: LatLng,
    currentViewModel: CurrentWeatherViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
) {
    println("Viewmodel init>>>")
//    println("Viewmodel init>>> State $state")
//    var currentLoc = defaultLocation
//    var loc = navController.currentBackStackEntry?.savedStateHandle?.get<String>("cood")
//    if (loc != null) {
//        currentLoc = Gson().fromJson(loc, LatLng::class.java)
//        loc = null
//    }
    sharedViewModel.updateLocation(defaultLocation, true)
    println("LOCATION selected>>> :: current $defaultLocation")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather Man") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondary,
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Favourites",
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddLocation.name)
            }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add")
            }
        },

    ) {
        CurrentWeatherScreen(
            currentLocation = Gson().toJson(defaultLocation),
            navController = navController,
        )
    }
}
