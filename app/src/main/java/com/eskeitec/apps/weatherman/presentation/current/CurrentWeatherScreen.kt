package com.eskeitec.apps.weatherman.presentation.current

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.eskeitec.apps.weatherman.R
import com.eskeitec.apps.weatherman.common.SetError
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.eskeitec.apps.weatherman.domain.model.WeatherType
import com.eskeitec.apps.weatherman.presentation.components.ErrorCard
import com.eskeitec.apps.weatherman.presentation.daysforecast.ForecastScreen
import com.eskeitec.apps.weatherman.ui.theme.GreenColor
import com.eskeitec.apps.weatherman.utils.Constants

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherScreen(
    navController: NavHostController,
    weatherViewModel: CurrentWeatherViewModel = hiltViewModel(),
) {
    println("Viewmodel init>>>")
//    val state = weatherViewModel.currentWeather.observeAsState().value
    val state1 = weatherViewModel.currentWeatherState.collectAsState()
    val state by weatherViewModel.currentWeatherState.collectAsState()
    println("Viewmodel init>>> State $state")
//    if (state == null) {
//        Text(
//            text = "Oops! Something Went Wrong",
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight()
//                .padding(32.dp),
//        )
//        return
//    }

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
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add")
            }
        },

    ) {
        BodyLayout(state = state)
    }
}

@Composable
fun BodyLayout(state: CurrentWeatherState) {
    return when (state) {
        is CurrentWeatherState.Loading -> {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }

        is CurrentWeatherState.Success -> {
            if (state.data == null) {
                ErrorCard(
                    modifier = Modifier.fillMaxSize(),
                    errorTitle = Constants.UNKNOWN_ERROR,
                    errorDescription = SetError.setErrorCard(
                        Constants.UNKNOWN_ERROR,
                    ),
                    errorButtonText = "Ok",
                    {},
                    cardModifier = Modifier
                        .fillMaxWidth()
                        .height(LocalConfiguration.current.screenHeightDp.dp / 4 + 48.dp)
                        .padding(horizontal = 64.dp),
                )
            }
            WeatherScreen(state.data!!)
        }

        is CurrentWeatherState.Error -> {
            ErrorCard(
                modifier = Modifier.fillMaxSize(),
                errorTitle = state.error
                    ?: Constants.UNKNOWN_ERROR,
                errorDescription = SetError.setErrorCard(
                    state.error ?: Constants.UNKNOWN_ERROR,
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
fun WeatherScreen(state: WeatherModel) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = GreenColor),
        ) {
            Box(modifier = Modifier.padding(0.dp), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(2f)
                        .height(400.dp),
                    painter = painterResource(id = getBgImage(state.weatherType)),
                    contentDescription = "background image",
                    contentScale = ContentScale.FillWidth,
                )

                Column(
                    modifier = Modifier
                        .padding(0.dp)
                        .align(TopCenter),
                ) {
                    Text(
                        text = "${state.city} ${state.country}",
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(vertical = 20.dp),
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

                    Text(
                        text = "${state.temp}º",
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(vertical = 0.dp),
                        style = TextStyle(
                            fontSize = 50.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.SansSerif,
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
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
            ForecastScreen()
        }
    }
}

fun getBgImage(weatherType: WeatherType): Int {
    return when (weatherType) {
        WeatherType.SUNNY -> R.drawable.forest_sunny
        WeatherType.WINDLY -> R.drawable.forest_cloudy
        WeatherType.RAINY -> R.drawable.forest_rainy
        else -> R.drawable.forest_cloudy
    }
}

fun getWeatherIcon(weatherType: WeatherType): Int {
    return when (weatherType) {
        WeatherType.SUNNY -> R.drawable.clear_3x
        WeatherType.WINDLY -> R.drawable.partlysunny_2x
        WeatherType.RAINY -> R.drawable.rain_3x
        else -> R.drawable.partlysunny_3x
    }
}
