package com.eskeitec.apps.weatherman.presentation.current

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.eskeitec.apps.weatherman.common.SetError
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.eskeitec.apps.weatherman.domain.model.toLocationEntity
import com.eskeitec.apps.weatherman.presentation.components.ErrorCard
import com.eskeitec.apps.weatherman.presentation.components.LoadingDialog
import com.eskeitec.apps.weatherman.presentation.daysforecast.ForecastScreen
import com.eskeitec.apps.weatherman.presentation.favourites.FavouritesViewModel
import com.eskeitec.apps.weatherman.utils.Constants
import com.eskeitec.apps.weatherman.utils.Utils.Companion.getBGColor
import com.eskeitec.apps.weatherman.utils.Utils.Companion.getBgImage
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

@Composable
fun CurrentWeatherScreen(
    weatherViewModel: CurrentWeatherViewModel = hiltViewModel(),
    navController: NavHostController,
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
    remember(weatherViewModel) {
        weatherViewModel.getCurrentWeatherData(
            "${currentLoc?.latitude}",
            "${currentLoc?.longitude}",
        )
    }
    val state: CurrentWeatherState by weatherViewModel.currentWeatherState.collectAsState()
    return when (state) {
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
                    (state as CurrentWeatherState.Error).error ?: Constants.DEFAULT_ERROR,
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

@Composable
fun WeatherScreen(
    state: WeatherModel,
    currentLoc: LatLng,
    weatherViewModel: CurrentWeatherViewModel,
    favouritesViewModel: FavouritesViewModel = hiltViewModel(),
) {
    val isFavourite = favouritesViewModel.isFavouriteAdded.observeAsState().value
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = getBGColor(state.weatherType)),
        ) {
            Box(modifier = Modifier.padding(0.dp), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(2f)
                        .height(300.dp),
                    painter = painterResource(id = getBgImage(state.weatherType)),
                    contentDescription = "background image",
                    contentScale = ContentScale.FillWidth,
                )

                Column(
                    modifier = Modifier
                        .padding(60.dp)
                        .align(TopCenter),
                ) {
                    Text(
                        text = "${state.city} ${state.country}",
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.SansSerif,
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )

                    Text(
                        text = "${state.date}",
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(vertical = 0.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily.SansSerif,
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                    Row(modifier = Modifier.padding(), horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = "${state.temp}º",
                            modifier = Modifier
                                .padding(vertical = 0.dp),
                            style = TextStyle(
                                fontSize = 50.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.SansSerif,
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 20.dp))
                        IconButton(
                            onClick = {
                                if (isFavourite == true) {
                                    favouritesViewModel.isFavouriteRemoved(state.toLocationEntity())
                                } else {
                                    favouritesViewModel.addLocationToFavourite(
                                        state.toLocationEntity(),
                                    )
                                }
                            },
                        ) {
                            if (isFavourite == true) {
                                Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = "Add to favourite",
                                    tint = Color.Red,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(top = 10.dp),
                                )
                            } else {
                                Icon(
                                    Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Add to favourite",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(top = 10.dp),
                                    tint = Color.LightGray,
                                )
                            }
                        }
                    }
                    Text(
                        text = state.weatherType.name,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(vertical = 0.dp),
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(2f)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = " ${state.minTemp}º \n Min",
                    modifier = Modifier.padding(horizontal = 0.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Start,

                )
                Text(
                    text = "${state.temp}º\nCurrent",
                    modifier = Modifier.padding(horizontal = 0.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                    ),
                    color = Color.White,
                    textAlign = TextAlign.End,
                )
                Text(
                    text = "${state.maxTemp}º\n Max",
                    modifier = Modifier.padding(horizontal = 0.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                    ),
                    color = Color.White,
                    textAlign = TextAlign.End,

                )
            }
            Divider(color = Color.White, thickness = 2.dp)

            ForecastScreen(currentLoc = currentLoc)
        }
    }
}
