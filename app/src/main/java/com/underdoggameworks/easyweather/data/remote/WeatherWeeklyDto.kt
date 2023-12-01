package com.underdoggameworks.easyweather.data.remote

import com.squareup.moshi.Json

data class WeatherWeeklyDto(
    @field:Json(name = "daily")
    val weatherDataDaily: WeatherWeeklyDataDto
)
