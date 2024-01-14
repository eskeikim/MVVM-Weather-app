package com.eskeitec.apps.weatherman.presentation

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eskeitec.apps.weatherman.common.extension.hasLocationPermission
import com.eskeitec.apps.weatherman.presentation.components.ComposeNavigation
import com.eskeitec.apps.weatherman.presentation.components.RationaleAlert
import com.eskeitec.apps.weatherman.presentation.states.PermissionEvent
import com.eskeitec.apps.weatherman.presentation.states.ViewState
import com.eskeitec.apps.weatherman.ui.theme.WeatherManTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val locationViewModel: MainActivityViewModel by viewModels()
        Timber.d("State INIT")

        setContent {
            WeatherManTheme {
                val permissionState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                    ),
                )

                val viewState by locationViewModel.viewState.collectAsState()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    LaunchedEffect(!hasLocationPermission()) {
                        permissionState.launchMultiplePermissionRequest()
                    }
                    checkAppPermissions(permissionState, locationViewModel = locationViewModel)
                    with(viewState) {
                        when (this) {
                            ViewState.Loading -> {
                                println("State Loading")
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    CircularProgressIndicator()
                                }
                            }

                            ViewState.RevokedPermissions -> {
                                println("State Revoked")
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(24.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text("You need  allow location permissions to use this app. This will enable the app to fetch your current location and display the weather data.")
                                    Spacer(modifier = Modifier.size(40.dp))
                                    Button(
                                        onClick = {
                                            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                                        },
                                        enabled = !hasLocationPermission(),
                                    ) {
                                        if (hasLocationPermission()) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(14.dp),
                                                color = Color.White,
                                            )
                                        } else {
                                            Text("Allow permission in Settings")
                                        }
                                    }
                                }
                            }

                            is ViewState.Success -> {
                                println("State Success")
                                val currentLoc =
                                    LatLng(
                                        this.location?.latitude ?: 0.0,
                                        this.location?.longitude ?: 0.0,
                                    )
                                ComposeNavigation(currentLoc)
                            }
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    @Composable
    private fun checkAppPermissions(
        permissionState: MultiplePermissionsState,
        locationViewModel: MainActivityViewModel,
    ) {
        when {
            permissionState.allPermissionsGranted -> {
                LaunchedEffect(Unit) {
                    locationViewModel.handle(PermissionEvent.Granted)
                }
            }

            permissionState.shouldShowRationale -> {
                RationaleAlert(onDismiss = {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }) {
                    permissionState.launchMultiplePermissionRequest()
                }
            }

            !permissionState.allPermissionsGranted || permissionState.shouldShowRationale -> {
                LaunchedEffect(Unit) {
                    locationViewModel.handle(PermissionEvent.Revoked)
                }
            }

//            !permissionState.allPermissionsGranted -> {
//                LaunchedEffect(Unit) {
//                    locationViewModel.handle(PermissionEvent.Revoked)
//                }
//            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Timber.d("TESTING LOGGER")
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherManTheme {
        Greeting("Android")
    }
}
