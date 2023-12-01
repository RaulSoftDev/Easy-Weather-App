package com.underdoggameworks.easyweather.data.remote

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto,
    @field:Json(name = "daily")
    val weatherDailyData: WeatherWeeklyDataDto
)
