package com.underdoggameworks.easyweather.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WeatherWeeklyData(
    state: WeatherState,
    modifier: Modifier
){
    state.weatherInfo?.weatherDataPerDay
}