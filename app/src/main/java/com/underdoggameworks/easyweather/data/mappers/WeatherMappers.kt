package com.underdoggameworks.easyweather.data.mappers

import com.underdoggameworks.easyweather.data.remote.WeatherDataDto
import com.underdoggameworks.easyweather.data.remote.WeatherDto
import com.underdoggameworks.easyweather.domain.weather.WeatherData
import com.underdoggameworks.easyweather.domain.weather.WeatherInfo
import com.underdoggameworks.easyweather.domain.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

//HOURLY DATA
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        val precipitationPrb = precipitationPrb[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode),
                precipitationPrb = precipitationPrb,
                weatherCode = weatherCode
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val weatherDailyDataMap = weatherDailyData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    val dailyWeatherData = weatherDailyDataMap[0]?.find {
        it.date == LocalDate.now()
    }
    return WeatherInfo(
        //HOURLY
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData,
        //DAILY
        weatherDataDaily = weatherDailyDataMap,
        weeklyWeatherData = dailyWeatherData
    )
}