package com.packt.XiaominGuo_COMP304Lab3_Ex1.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    //Get current weather data from a remote server using specified latitude and longitude
    @GET("forecast")
    suspend fun fetchWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m,wind_speed_10m"
    ): Response<WeatherResponse>

}