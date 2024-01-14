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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eskeitec.apps.weatherman.domain.model.WeatherModel
import com.eskeitec.apps.weatherman.presentation.current.getBgImage
import com.eskeitec.apps.weatherman.presentation.current.getWeatherIcon

@Composable
fun ForecastScreen(
    forecastViewModel: ForecastWeatherViewModel = hiltViewModel(),
) {
    val state = forecastViewModel.forecastWeather.observeAsState().value
    if (state == null || state?.weatherModel?.isEmpty() == true) return
    println("FORECAST ${state?.city} ${state?.weatherModel?.first()}")

    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state.weatherModel) { item ->
                DayForecastItem(item)
            }
        }
    }
}

@Composable
fun DayForecastItem(item: WeatherModel) {
    Row(modifier = Modifier.padding(20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "${item.date}")
        Image(

            painter = painterResource(id = getWeatherIcon(item.weatherType)),
            contentDescription = "background image",
            modifier = Modifier.size(40.dp).padding(10.dp),
        )
        Text(text = "${item.temp}º")
    }
}
