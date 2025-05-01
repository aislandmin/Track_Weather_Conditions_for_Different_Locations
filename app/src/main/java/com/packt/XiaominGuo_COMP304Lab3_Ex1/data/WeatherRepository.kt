package com.packt.XiaominGuo_COMP304Lab3_Ex1.data

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(): Flow<List<Weather>>
    suspend fun fetchRemoteWeather()
    suspend fun updateWeather(weather: Weather)
    suspend fun getFavoriteWeather(): Flow<List<Weather>>
}