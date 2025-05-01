package com.packt.XiaominGuo_COMP304Lab3_Ex1.data

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private  val weatherAPI: WeatherAPI,
    private val dispatcher: CoroutineDispatcher,
    private val weatherDao: WeatherDao
): WeatherRepository {
    override suspend fun getWeather(): Flow<List<Weather>> {
        return withContext(dispatcher) {
           weatherDao.getWeather()
               .map { weatherCached ->
                   weatherCached.map { weatherEntity ->
                       Weather(
                           id = weatherEntity.id,
                           location = weatherEntity.location,
                           temperature = weatherEntity.temperature,
                           windSpeed = weatherEntity.windSpeed,
                           isFavorite = weatherEntity.isFavorite
                       )
                   }
               }
        }
    }

    override suspend fun fetchRemoteWeather() {
        withContext(dispatcher) {
            //clear weather table in database before storing
            weatherDao.clearWeather()

            locationList.forEach { location ->
                try {
                    // Fetch weather data for the location
                    val response = weatherAPI.fetchWeather(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )

                    if (response.isSuccessful) {
                        val weatherResponse = response.body()
                        weatherResponse?.let {
                            println("Temperature: ${it.current.temperature}°C")
                            println("Wind Speed: ${it.current.windSpeed} km/h")
                            // Create WeatherEntity and insert into the database
                            val weatherEntity = WeatherEntity(
                                location = location.name,
                                temperature = it.current.temperature,
                                windSpeed = it.current.windSpeed,
                                isFavorite = false
                            )
                            weatherDao.insert(weatherEntity)
                            Log.d("Weather", "Inserted data for ${location.name}")
                        }
                    } else {
                        Log.e("Weather", "Error fetching data for ${location.name}")
                    }
                } catch (e: Exception) {
                    Log.e("Weather", "Exception: ${e.message}")
                }
            }
        }
    }

    override suspend fun updateWeather(weather: Weather) {
        withContext(dispatcher) {
            weatherDao.update(WeatherEntity(
                id = weather.id,
                location = weather.location,
                temperature = weather.temperature,
                windSpeed = weather.windSpeed,
                isFavorite = weather.isFavorite
            ))
        }
    }

    override suspend fun getFavoriteWeather(): Flow<List<Weather>> {
        return withContext(dispatcher) {
            weatherDao.getFavoriteWeather()
                .map { weatherCached ->
                    weatherCached.map { weatherEntity ->
                        Weather(
                            id = weatherEntity.id,
                            location = weatherEntity.location,
                            temperature = weatherEntity.temperature,
                            windSpeed = weatherEntity.windSpeed,
                            isFavorite = weatherEntity.isFavorite
                        )
                    }
                }
        }
    }
}