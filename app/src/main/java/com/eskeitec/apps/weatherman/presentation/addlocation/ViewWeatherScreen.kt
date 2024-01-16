package com.eskeitec.apps.weatherman.presentation.addlocation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.eskeitec.apps.weatherman.common.SetError
import com.eskeitec.apps.weatherman.presentation.Screen
import com.eskeitec.apps.weatherman.presentation.components.ErrorCard
import com.eskeitec.apps.weatherman.presentation.components.LoadingDialog
import com.eskeitec.apps.weatherman.presentation.current.CurrentWeatherState
import com.eskeitec.apps.weatherman.presentation.current.CurrentWeatherViewModel
import com.eskeitec.apps.weatherman.presentation.current.WeatherScreen
import com.eskeitec.apps.weatherman.utils.Constants
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewWeatherScreen(
    navController: NavHostController,
    weatherViewModel: CurrentWeatherViewModel = hiltViewModel(),
    currentLocation: String,

) {
    var currentLoc: LatLng? = if (currentLocation.isNotEmpty()) {
        Gson().fromJson(
            currentLocation,
            LatLng::class.java,
        )
    } else {
        return
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Selected Location") },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack(Screen.Home.name, false)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "back navigation icon",
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
                navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            ),
        )
    }) { innerPaddding ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .padding(innerPaddding),

        ) {
            remember(weatherViewModel) {
                weatherViewModel.getCurrentWeatherData(
                    "${currentLoc?.latitude}",
                    "${currentLoc?.longitude}",
                )
            }

            val state: CurrentWeatherState by weatherViewModel.currentWeatherState.collectAsState()
            when (state) {
                is CurrentWeatherState.Loading -> {
                    LoadingDialog()
                }

                is CurrentWeatherState.Success -> {
                    if ((state as CurrentWeatherState.Success).data == null) {
                        ErrorCard(
                            modifier = Modifier.fillMaxSize(),
                            errorTitle = Constants.DEFAULT_ERROR,
                            errorDescription = SetError.setErrorCard(
                                Constants.DEFAULT_ERROR,
                            ),
                            errorButtonText = "Ok",
                            {},
                            cardModifier = Modifier
                                .fillMaxWidth()
                                .height(LocalConfiguration.current.screenHeightDp.dp / 4 + 48.dp)
                                .padding(horizontal = 64.dp),
                        )
                    }
                    WeatherScreen(
                        (state as CurrentWeatherState.Success).data!!,
                        currentLoc!!,
                        weatherViewModel,
                    )
                }

                is CurrentWeatherState.Error -> {
                    ErrorCard(
                        modifier = Modifier.fillMaxSize(),
                        errorTitle = (state as CurrentWeatherState.Error).error
                            ?: Constants.DEFAULT_ERROR,
                        errorDescription = SetError.setErrorCard(
                            (state as CurrentWeatherState.Error).error
                                ?: Constants.DEFAULT_ERROR,
                        ),
                        errorButtonText = "Ok",
                        {},
                        cardModifier = Modifier
                            .fillMaxWidth()
                            .height(LocalConfiguration.current.screenHeightDp.dp / 5 + 48.dp)
                            .padding(horizontal = 64.dp),
                    )
                }
            }
        }
    }
}
