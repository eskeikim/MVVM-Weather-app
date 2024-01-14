package com.eskeitec.apps.weatherman.presentation.daysforecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.eskeitec.apps.weatherman.utils.Utils.Companion.getWeatherIcon
import com.google.android.gms.maps.model.LatLng

@Composable
fun ForecastScreen(
    forecastViewModel: ForecastWeatherViewModel = hiltViewModel(),
    currentLoc: LatLng,
) {
    forecastViewModel.getForecastWeatherData("${currentLoc.latitude}", "${currentLoc.longitude}")
    val state = forecastViewModel.forecastWeather.observeAsState().value
    if (state == null || state.weatherModel.isEmpty()) return
    println("FORECAST ${state.city} ${state.weatherModel?.first()}")

    Box(modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state.weatherModel) { item ->
                DayForecastItem(item)
            }
        }
    }
}

@Composable
fun DayForecastItem(item: WeatherModel) {
    Row(
        modifier = Modifier.padding(20.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "${item.date}",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
            ),
            color = Color.White,
        )
        Image(
            painter = painterResource(id = getWeatherIcon(item.weatherType)),
            contentDescription = "background image",
            modifier = Modifier.size(40.dp).padding(10.dp),
        )
        Text(
            text = "${item.temp}º",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
            ),
            color = Color.White,
        )
    }
}
