package com.underdoggameworks.easyweather.domain.weather

data class WeatherDailyInfo(
    val weatherDataDaily: Map<Int, List<WeatherDailyData>>,
    val currentDailyWeatherData: WeatherDailyData?
)
