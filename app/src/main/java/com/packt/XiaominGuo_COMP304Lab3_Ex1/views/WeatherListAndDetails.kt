package com.packt.XiaominGuo_COMP304Lab3_Ex1.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.Weather

@Composable
fun WeatherListAndDetails(
    weather: List<Weather>,
    onFavoriteClicked: (Weather) -> Unit
) {
    var currentWeather by remember {
        mutableStateOf(weather.first())
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WeatherList(
            onWeatherClicked = {
                currentWeather = it
            },
            weather = weather,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onFavoriteClicked = onFavoriteClicked
        )
        WeatherDetailsScreenContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f),
            weather = currentWeather
        )
    }
}