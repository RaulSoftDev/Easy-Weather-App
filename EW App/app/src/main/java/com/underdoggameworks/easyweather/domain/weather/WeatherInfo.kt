package com.underdoggameworks.easyweather.domain.weather

import androidx.annotation.Keep

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val weatherDataDaily: Map<Int, List<WeatherDailyData>>,
    val currentWeatherData: WeatherData?,
    val weeklyWeatherData: WeatherDailyData?
)
