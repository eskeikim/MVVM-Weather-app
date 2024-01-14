package com.eskeitec.apps.weatherman.presentation.add_location

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eskeitec.apps.weatherman.presentation.Screen

@Composable
fun AddLocationScreen(
    navController: NavController,
    viewModel: AddLocationViewModel = hiltViewModel(),
) {
    viewModel.getPlaces("Nairobi")
    val state = viewModel.loading.value
    if (state) {
        return Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }

    val placesState = viewModel.places.value

    if (placesState.isEmpty()) {
        Text(text = "Empty results, try another query")
    } else {
        println("Loaded data ${placesState.size} :: ${placesState.first()}")
    }
    val navController = rememberNavController()
    NavHost(navController, Screen.Current.name) {
        composable(route = Screen.Current.name) {
//            CurrentWeatherScreen(navController, currentLoc = currentLoc)
        }
    }
}
