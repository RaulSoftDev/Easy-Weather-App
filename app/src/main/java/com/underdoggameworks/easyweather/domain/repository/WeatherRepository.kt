package com.underdoggameworks.easyweather.domain.repository

import com.underdoggameworks.easyweather.domain.util.Resource
import com.underdoggameworks.easyweather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}