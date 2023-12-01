package com.underdoggameworks.easyweather.navigation

import android.location.Geocoder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.underdoggameworks.easyweather.presentation.WeatherViewModel
import com.underdoggameworks.easyweather.presentation.WeekScreen
import com.underdoggameworks.easyweather.presentation.MainScreen

@ExperimentalMaterial3Api
@Composable
fun AppNavigation(viewModel: WeatherViewModel, geocoder: Geocoder){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route){
        composable(route = AppScreens.MainScreen.route){
            MainScreen(navController = navController, viewModel = viewModel, geocoder = geocoder)
        }
        composable(route = AppScreens.WeekScreen.route){
            WeekScreen(navController = navController, viewModel = viewModel)
        }
    }
}