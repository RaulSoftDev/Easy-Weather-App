package com.underdoggameworks.easyweather.data.repository

import com.underdoggameworks.easyweather.domain.repository.WeatherRepository
import com.underdoggameworks.easyweather.domain.util.Resource
import com.underdoggameworks.easyweather.domain.weather.WeatherInfo
import com.underdoggameworks.easyweather.data.mappers.toWeatherInfo
import com.underdoggameworks.easyweather.data.remote.WeatherApi
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}