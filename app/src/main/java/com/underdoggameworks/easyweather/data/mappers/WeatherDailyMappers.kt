package com.underdoggameworks.easyweather.data.mappers

import com.underdoggameworks.easyweather.data.remote.WeatherWeeklyDataDto
import com.underdoggameworks.easyweather.domain.weather.WeatherDailyData
import com.underdoggameworks.easyweather.domain.weather.WeatherType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private data class IndexedDailyWeatherData(
    val index: Int,
    val data: WeatherDailyData
)

fun WeatherWeeklyDataDto.toWeatherDataMap(): Map<Int, List<WeatherDailyData>> {
    return time.mapIndexed { index, time ->
        val maxTemperature = temperaturesMax[index]
        val minTemperature = temperaturesMin[index]
        val weatherCode = dailyWeatherCodes[index]
        val meanPrecipitation = meanPrecipitation[index]
        val windSpeed = windSpeed[index]
        IndexedDailyWeatherData(
            index = index,
            data = WeatherDailyData(
                date = LocalDate.parse(time, DateTimeFormatter.ISO_LOCAL_DATE),
                maxTemperatureCelsius = maxTemperature,
                minTemperatureCelsius = minTemperature,
                weatherType = WeatherType.fromWMO(weatherCode),
                meanPrecipitation = meanPrecipitation,
                windSpeed = windSpeed
            )
        )
    }.groupBy {
        it.index / 8
    }.mapValues {
        it.value.map { it.data }
    }
}