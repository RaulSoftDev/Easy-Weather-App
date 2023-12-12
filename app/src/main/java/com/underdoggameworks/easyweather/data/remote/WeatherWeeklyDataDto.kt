package com.underdoggameworks.easyweather.data.remote

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class WeatherWeeklyDataDto(
    val time: List<String>,
    @field:Json(name = "temperature_2m_max")
    val temperaturesMax: List<Double>,
    @field:Json(name = "temperature_2m_min")
    val temperaturesMin: List<Double>,
    @field:Json(name = "weathercode")
    val dailyWeatherCodes: List<Int>,
    @field:Json(name = "precipitation_probability_mean")
    val meanPrecipitation: List<Double>,
    @field:Json(name = "wind_speed_10m_max")
    val windSpeed: List<Double>
)
