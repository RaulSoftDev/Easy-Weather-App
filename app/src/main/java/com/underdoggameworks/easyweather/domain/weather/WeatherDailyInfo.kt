package com.underdoggameworks.easyweather.domain.weather

import androidx.annotation.Keep

data class WeatherDailyInfo(
    val weatherDataDaily: Map<Int, List<WeatherDailyData>>,
    val currentDailyWeatherData: WeatherDailyData?
)
