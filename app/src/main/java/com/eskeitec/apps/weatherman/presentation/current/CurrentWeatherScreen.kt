package com.eskeitec.apps.weatherman.presentation.current

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.eskeitec.apps.weatherman.domain.model.WeatherType
import com.eskeitec.apps.weatherman.ui.theme.GreenColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherScreen(
    navController: NavHostController,
    weatherViewModel: CurrentWeatherViewModel = hiltViewModel(),
) {
    println("Viewmodel init>>>")
    val state = weatherViewModel.currentWeather.observeAsState().value
    println("Viewmodel init>>> ${state?.temp}")
    if (state == null) {
        Text(
            text = "Oops! Something Went Wrong",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(32.dp),
        )
        return
    }
//    val temp= Integer.parseInt(state.temp.toDouble())

    Card(modifier = Modifier.fillMaxSize().background(color = Color.Gray)) {
        Column(modifier = Modifier.fillMaxSize().background(color = GreenColor)) {
            Box(modifier = Modifier.padding(0.dp), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.fillMaxWidth(2f).height(400.dp),
                    painter = painterResource(id = getBgImage(state.weatherType)),
                    contentDescription = "background image",
                    contentScale = ContentScale.FillWidth,
                )

                Column(modifier = Modifier.padding(0.dp).align(Center)) {
                    Text(
                        text = "${state.temp}º",
                        modifier = Modifier.align(CenterHorizontally).padding(vertical = 0.dp),
                        style = TextStyle(
                            fontSize = 35.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.SansSerif,
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = state.weatherType.name,
                        modifier = Modifier.align(CenterHorizontally).padding(vertical = 50.dp),
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
                modifier = Modifier.fillMaxWidth(2f).padding(10.dp),
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
