package com.packt.XiaominGuo_COMP304Lab3_Ex1.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.NetworkResult
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.WeatherRepository
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.Weather
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.asResult
import com.packt.XiaominGuo_COMP304Lab3_Ex1.views.WeatherUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
): ViewModel() {
    val weatherUIState = MutableStateFlow(WeatherUIState())
    private val _favoriteWeather = MutableStateFlow<List<Weather>>(emptyList())
    val favoriteWeather: StateFlow<List<Weather>> get() = _favoriteWeather

    init {
        getWeather()
    }

    private fun getWeather() {
        weatherUIState.value = WeatherUIState(isLoading = true)
        viewModelScope.launch {
            weatherRepository.getWeather().asResult().collect { result ->
                when (result ) {
                    is NetworkResult.Success -> {
                        weatherUIState.update {
                            it.copy(isLoading = false, weather = result.data)
                        }
                    }
                    is NetworkResult.Error -> {
                        weatherUIState.update {
                            it.copy(isLoading = false, error = result.error)
                        }
                    }
                }
            }
        }
        Log.d("WeatherViewModel", "Weather UI State: $weatherUIState")

    }

    fun updateWeather(weather: Weather) {
        viewModelScope.launch {
            weatherRepository.updateWeather(weather)
        }
    }

    fun getFavoriteWeather() {
        viewModelScope.launch {
            weatherRepository.getFavoriteWeather().collect {
                _favoriteWeather.value = it
            }
        }
    }
}