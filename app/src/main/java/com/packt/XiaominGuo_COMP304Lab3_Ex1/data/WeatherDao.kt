package com.packt.XiaominGuo_COMP304Lab3_Ex1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM Weather")
    fun getWeather(): Flow<List<WeatherEntity>>

    @Update
    suspend fun update(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM Weather WHERE isFavorite = 1")
    fun getFavoriteWeather(): Flow<List<WeatherEntity>>

    @Query("DELETE FROM Weather")
    suspend fun clearWeather()
}