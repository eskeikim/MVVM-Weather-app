package com.eskeitec.apps.weatherman.presentation

sealed class Screen(val name: String) {
    object Home : Screen("home_screen")
    object Current : Screen("current_weather_screen")
    object AddLocation : Screen("add_location_screen")
    object ViewWeatherScreen : Screen("view_selected_location_screen")
    object ListFavouritesScreen : Screen("list_favourites_screen")
}
