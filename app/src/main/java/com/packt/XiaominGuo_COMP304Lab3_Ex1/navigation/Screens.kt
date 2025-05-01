package com.packt.XiaominGuo_COMP304Lab3_Ex1.navigation

sealed class Screens(val route: String) {
    object WeatherScreen : Screens("weather")
    object WeatherDetailsScreen : Screens("weatherDetails")
    object FavoriteWeatherScreen : Screens("favoriteWeather")
}
