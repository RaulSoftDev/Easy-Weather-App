package com.underdoggameworks.easyweather.data.remote

import androidx.annotation.Keep
import retrofit2.http.GET
import retrofit2.http.Query

@Keep
interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl,precipitation_probability&daily=temperature_2m_max,temperature_2m_min,weathercode,precipitation_probability_mean,wind_speed_10m_max&forecast_days=8")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDto
}