package com.underdoggameworks.easyweather.domain.weather

import androidx.annotation.Keep
import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType,
    val weatherCode: Int,
    val precipitationPrb: Double
)
