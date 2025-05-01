package com.packt.XiaominGuo_COMP304Lab3_Ex1.data

//Location definition
data class Location(
    val name: String,
    val latitude: Double,
    val longitude: Double
)

// List of locations with coordinates
val locationList = listOf(
    Location("Berlin", 52.52, 13.41),
    Location("Toronto", 43.7, -79.42),
    Location("New York", 40.71, -74.01),
    Location("Tokyo", 35.68, 139.76),
    Location("London", 51.51, -0.13),
    Location("Paris", 48.85, 2.35),
    Location("Sydney", -33.87, 151.21),
    Location("Dubai", 25.20, 55.27),
    Location("Los Angeles", 34.05, -118.25),
    Location("Moscow", 55.75, 37.62),
    Location("Cape Town", -33.92, 18.42),
    Location("Rio de Janeiro", -22.90, -43.19),
    Location("Hong Kong", 22.28, 114.17),
    Location("Seoul", 37.56, 126.97),
    Location("Mexico City", 19.43, -99.13)
)