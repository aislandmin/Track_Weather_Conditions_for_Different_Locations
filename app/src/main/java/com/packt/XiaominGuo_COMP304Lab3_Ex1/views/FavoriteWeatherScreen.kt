package com.packt.XiaominGuo_COMP304Lab3_Ex1.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.Weather
import com.packt.XiaominGuo_COMP304Lab3_Ex1.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteWeatherScreen(
    onWeatherClicked: (Weather) -> Unit
) {
    val weatherViewModel: WeatherViewModel = koinViewModel()
    LaunchedEffect(Unit) {
        weatherViewModel.getFavoriteWeather()
    }
    val items by weatherViewModel.favoriteWeather.collectAsStateWithLifecycle()

    if (items.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No favorite weather")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(items) { item ->
                WeatherListItem(
                    weather = item,
                    onWeatherClicked = onWeatherClicked,
                    onFavoriteClicked = {
                        weatherViewModel.updateWeather(it)
                    }
                )
            }
        }
    }
}