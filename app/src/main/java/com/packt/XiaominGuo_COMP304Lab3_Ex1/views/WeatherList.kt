package com.packt.XiaominGuo_COMP304Lab3_Ex1.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.Weather

@Composable
fun WeatherList(
    onWeatherClicked: (Weather) -> Unit,
    weather: List<Weather>,
    modifier: Modifier,
    onFavoriteClicked: (Weather) -> Unit
) {
    val weatherSize = weather.size
    Log.d("WeatherList", "Weather list size: $weatherSize")
    LazyColumn(
        modifier = modifier
    ) {
        items(weather) { singleWeather ->
            WeatherListItem(
                weather = singleWeather,
                onWeatherClicked = onWeatherClicked,
                onFavoriteClicked = onFavoriteClicked
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WeatherListItem(
    weather: Weather,
    onWeatherClicked: (Weather) -> Unit,
    onFavoriteClicked: (Weather) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    onWeatherClicked(weather)
                }
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 6.dp, end = 6.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f) // This ensures the content takes full width of the row
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = weather.location,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${weather.temperature} °C",
                        color = Color.Gray
                    )
                }
                Icon(
                    modifier = Modifier
                        .clickable {
                            onFavoriteClicked(weather.copy(isFavorite = !weather.isFavorite))
                        },
                    imageVector = if (weather.isFavorite) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = "Favorite",
                    tint = if (weather.isFavorite) {
                        Color.Red
                    } else {
                        Color.Gray
                    },
                )
            }
        }
    }
}