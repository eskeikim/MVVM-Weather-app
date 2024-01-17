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
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.eskeitec.apps.weatherman.presentation.Screen
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    defaultLocation: LatLng,
) {
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
                    IconButton(onClick = {
                        navController.navigate(Screen.ListFavouritesScreen.name)
                    }) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Favourites",
                            tint = Color.Red,
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
