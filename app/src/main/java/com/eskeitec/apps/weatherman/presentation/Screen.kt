package com.eskeitec.apps.weatherman.presentation

sealed class Screen(val name: String) {
    object current : Screen("current_weather_screen")
}
