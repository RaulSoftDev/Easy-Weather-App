package com.underdoggameworks.easyweather.data.remote

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto,
    @field:Json(name = "daily")
    val weatherDailyData: WeatherWeeklyDataDto
)
