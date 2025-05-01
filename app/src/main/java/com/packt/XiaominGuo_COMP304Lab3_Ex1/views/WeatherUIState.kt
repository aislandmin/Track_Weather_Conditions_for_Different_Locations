package com.packt.XiaominGuo_COMP304Lab3_Ex1.views

import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.Weather

data class WeatherUIState(
    val isLoading: Boolean = false,
    val weather: List<Weather> = emptyList(),
    val error: String? = null
)
