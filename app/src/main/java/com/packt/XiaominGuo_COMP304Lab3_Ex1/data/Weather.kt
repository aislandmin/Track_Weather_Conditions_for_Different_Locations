package com.packt.XiaominGuo_COMP304Lab3_Ex1.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("id")
    val id: Int,
    @SerialName("location")
    val location: String = "",
    @SerialName("temperature_2m")
    val temperature: Double,
    @SerialName("wind_speed_10m")
    val windSpeed: Double,
    val isFavorite: Boolean = false
)

@Serializable
data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    val elevation: Double,
    @SerialName("current_units")
    val currentUnits: CurrentUnits,
    val current: CurrentWeather
)

@Serializable
data class CurrentUnits(
    val time: String,
    val interval: String,
    @SerialName("temperature_2m")
    val temperatureUnit: String,
    @SerialName("wind_speed_10m")
    val windSpeedUnit: String
)

@Serializable
data class CurrentWeather(
    val time: String,
    val interval: Int,
    @SerialName("temperature_2m")
    val temperature: Double,
    @SerialName("wind_speed_10m")
    val windSpeed: Double
)
