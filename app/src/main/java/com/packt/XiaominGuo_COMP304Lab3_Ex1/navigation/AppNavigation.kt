package com.packt.XiaominGuo_COMP304Lab3_Ex1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.packt.XiaominGuo_COMP304Lab3_Ex1.views.FavoriteWeatherScreen
import com.packt.XiaominGuo_COMP304Lab3_Ex1.views.WeatherDetailsScreen
import com.packt.XiaominGuo_COMP304Lab3_Ex1.views.WeatherScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation(
    contentType: ContentType,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        //When start app, enter this screen by default
        startDestination = Screens.WeatherScreen.route
    ) {
        composable(Screens.WeatherScreen.route) {
            WeatherScreen(
                onWeatherClicked = { weather ->
                    navHostController.navigate(
                        "${Screens.WeatherDetailsScreen.route}/${Json.encodeToString(weather)}"
                    )
                },
                contentType = contentType
            )
        }
        composable(
            route = "${Screens.WeatherDetailsScreen.route}/{weather}",
            arguments = listOf(
                navArgument("weather") {
                    type = NavType.StringType
                }
            )
        ) {
            WeatherDetailsScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                weather = Json.decodeFromString(it.arguments?.getString("weather") ?: "")
            )
        }
        composable(Screens.FavoriteWeatherScreen.route) {
            FavoriteWeatherScreen(
                onWeatherClicked = { weather ->
                    navHostController.navigate(
                        "${Screens.WeatherDetailsScreen.route}/${Json.encodeToString(weather)}"
                    )
                }
            )
        }
    }
}