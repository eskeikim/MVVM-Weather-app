package com.eskeitec.apps.weatherman.presentation.addlocation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.twotone.LocationOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.eskeitec.apps.weatherman.common.SetError
import com.eskeitec.apps.weatherman.presentation.Screen
import com.eskeitec.apps.weatherman.presentation.components.ErrorCard
import com.eskeitec.apps.weatherman.presentation.components.LoadingDialog
import com.eskeitec.apps.weatherman.presentation.current.CityDetailsState
import com.eskeitec.apps.weatherman.presentation.shared.SharedViewModel
import com.eskeitec.apps.weatherman.utils.Constants
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLocationScreen(
    navController: NavHostController,
    viewModel: AddLocationViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
) {
    val state = viewModel.loading.value
    if (state) {
        LoadingDialog()
    }

    val placesLocation = viewModel.locationPlaces.collectAsState().value

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Add location") },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.navigateUp()
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
            modifier = Modifier
                .padding(innerPaddding),

        ) {
            Surface(shadowElevation = 2.dp, color = MaterialTheme.colorScheme.background) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 8.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        OutlinedTextField(
                            value = viewModel.searchQuery,
                            onValueChange = viewModel::onPlaceValueChanged,
                            singleLine = true,
                            placeholder = {
                                Text(text = "Search location")
                            },
                            trailingIcon = {
                                if (viewModel.searchQuery.isNotEmpty()) {
                                    Icon(
                                        imageVector = Icons.Default.Send,
                                        contentDescription = null,
                                    )
                                }
                            },
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier.fillMaxWidth(0.9f),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Search,
                            ),
                        )
                    }
                }
            }
            LazyColumn {
                items(placesLocation) { place ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                viewModel.onPlaceClick(place.placeId.toString())
                            },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp),
                        )
                        Column {
                            Text(text = place.description.toString())
                            Text(text = place.structuredFormatting?.mainText?:"")
                        }
                    }
                }
            }
            val cityDetailsState = viewModel.cityDetails.collectAsState().value
            when (cityDetailsState) {
                is CityDetailsState.Loading -> {
                    println("Loading.... Get Coodinates.")
                    LoadingDialog()
                }

                is CityDetailsState.Success -> {
                    val data = cityDetailsState.data

                    if (data == null) {
                        println("Error.... Get ERROR.")
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
                    } else {
                        val selectedLocation =
                            LatLng(data.geometry?.location?.lat!!, data.geometry.location.lng!!)
                        println("Success .... Get $selectedLocation.")

                        val loc = Gson().toJson(selectedLocation)
                        navController.navigate(
                            "${Screen.ViewWeatherScreen.name}/$loc",
                        )
                    }
                }

                else -> {}
            }
        }
    }
}
