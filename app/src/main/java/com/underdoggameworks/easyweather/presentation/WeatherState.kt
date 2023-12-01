package com.underdoggameworks.easyweather.presentation

import com.underdoggameworks.easyweather.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
