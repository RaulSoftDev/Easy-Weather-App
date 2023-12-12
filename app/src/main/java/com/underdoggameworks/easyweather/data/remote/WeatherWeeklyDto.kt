package com.underdoggameworks.easyweather.data.remote

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class WeatherWeeklyDto(
    @field:Json(name = "daily")
    val weatherDataDaily: WeatherWeeklyDataDto
)
