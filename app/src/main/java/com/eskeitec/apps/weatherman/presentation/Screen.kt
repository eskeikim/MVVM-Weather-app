package com.eskeitec.apps.weatherman.presentation

sealed class Screen(val name: String) {
    object Current : Screen("current_weather_screen")
    object AddLocation : Screen("add_location_screen")
}
