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
    Card(modifier = Modifier.fillMaxSize().background(color = Color.Green)) {
        Column(modifier = Modifier.padding(8.dp).fillMaxSize().background(color = Color.Green)) {
            Box(modifier = Modifier.padding(0.dp), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.fillMaxWidth().height(400.dp),
                    painter = painterResource(id = R.drawable.forest_cloudy),
                    contentDescription = "background image",
                    contentScale = ContentScale.FillWidth,
                )

                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "${state.temp}º",
                        modifier = Modifier.align(CenterHorizontally).padding(vertical = 20.dp),
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = state.weatherType.name,
                        modifier = Modifier.align(CenterHorizontally).padding(vertical = 10.dp),
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center,

                        )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp).background(color = Color.Green),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "${state.minTemp}º \n min",
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
                    text = "${state.temp}º\n current",
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
                    text = "${state.maxTemp}º\n max",
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
        }
    }
}
