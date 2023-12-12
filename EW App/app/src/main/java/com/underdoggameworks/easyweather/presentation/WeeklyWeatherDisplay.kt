package com.underdoggameworks.easyweather.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.underdoggameworks.easyweather.R
import com.underdoggameworks.easyweather.domain.weather.WeatherDailyData
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun WeeklyWeatherDisplay(
    weatherData: WeatherDailyData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
){
    val formattedDate = remember(weatherData) {
        weatherData.date.format(
            DateTimeFormatter.ofPattern("EEEE").withLocale(Locale.ENGLISH)
        )
    }
    Card(
        backgroundColor = ThemeColors.window.copy(alpha = 0.3f),
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp
    ){
        dailyInfoCard(
            weatherData = weatherData,
            formattedDate = formattedDate,
            textColor = textColor
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun dailyInfoCard(weatherData: WeatherDailyData, formattedDate: String, textColor: Color){
    Column(
        modifier = Modifier.padding(16.dp).width(275.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = weatherData.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier
                    .width(125.dp)
                    .height(75.dp)
                    .align(Alignment.CenterVertically),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.align(Alignment.CenterVertically).width(125.dp).height(75.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = formattedDate,
                    color = textColor,
                    fontSize = 19.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${weatherData.minTemperatureCelsius}°/${weatherData.maxTemperatureCelsius}°",
                    color = textColor,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = weatherData.weatherType.weatherDesc,
                    color = textColor,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        Row (
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Column(
                modifier = Modifier.align(Alignment.CenterVertically).width(72.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_drop),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp).padding(bottom = 4.dp),
                    colorFilter = ColorFilter.tint(Color.White),
                    alignment = Alignment.Center
                )
                Text(
                    text = "${weatherData.meanPrecipitation}%",
                    color = textColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Rain",
                    color = textColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier.align(Alignment.CenterVertically).width(72.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_wind),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp).padding(bottom = 4.dp),
                    colorFilter = ColorFilter.tint(Color.White),
                    alignment = Alignment.Center
                )
                Text(
                    text = "${weatherData.windSpeed}Km/h",
                    color = textColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Wind",
                    color = textColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}