package com.underdoggameworks.easyweather.domain.weather

import java.time.LocalDate

data class WeatherDailyData(
    val date: LocalDate,
    val maxTemperatureCelsius: Double,
    val minTemperatureCelsius: Double,
    val weatherType: WeatherType,
    val meanPrecipitation: Double,
    val windSpeed: Double
)
