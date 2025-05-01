package com.packt.XiaominGuo_COMP304Lab3_Ex1.views

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.Weather
import com.packt.XiaominGuo_COMP304Lab3_Ex1.navigation.ContentType
import com.packt.XiaominGuo_COMP304Lab3_Ex1.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen(
    onWeatherClicked: (Weather) -> Unit,
    contentType: ContentType,
) {
    val weatherViewModel: WeatherViewModel = koinViewModel()
    val weatherUIState by weatherViewModel.weatherUIState.collectAsStateWithLifecycle()
    Log.d("WeatherScreen", "Weather UI State: $weatherUIState")
    WeatherScreenContent(
        modifier = Modifier
            .fillMaxSize(),
        onWeatherClicked = onWeatherClicked,
        contentType = contentType,
        weatherUIState = weatherUIState,
        onFavoriteClicked = {
            weatherViewModel.updateWeather(it)
        }
    )
}