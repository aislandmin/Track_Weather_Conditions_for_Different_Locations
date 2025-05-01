package com.packt.XiaominGuo_COMP304Lab3_Ex1.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Auto-increment primary key
    val location: String,
    val temperature: Double,
    val windSpeed: Double,
    @ColumnInfo(defaultValue = "0")
    val isFavorite: Boolean
)

