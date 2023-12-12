package com.underdoggameworks.easyweather.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.underdoggameworks.easyweather.navigation.AppScreens
import java.time.LocalDateTime

@Composable
fun WeatherForecast(
    state: WeatherState,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Card(
                backgroundColor = ThemeColors.window.copy(alpha = 0.3f),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier.padding(16.dp).width(650.dp).height(300.dp).align(Alignment.Center),
                elevation = 0.dp
            ){
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Today",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.weight(1f))
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = { navController.navigate(route = AppScreens.WeekScreen.route) },
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = ThemeColors.button, contentColor = Color.White),
                                elevation = ButtonDefaults.elevation(
                                    defaultElevation = 2.dp,
                                    pressedElevation = 8.dp,
                                    disabledElevation = 0.dp,
                                    hoveredElevation = 4.dp,
                                    focusedElevation = 4.dp)
                            )
                            {
                                Text(
                                    text = "Week",
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                    LazyRow(content = {
                        items(data) { weatherData ->
                            //Check if current day hour is prior to 20:00h
                            if(weatherData.time.hour < 20){
                                //Check if current hour minute is prior to 30
                                if(LocalDateTime.now().minute < 30){
                                    if(weatherData.time >= LocalDateTime.now()){
                                        HourlyWeatherDisplay(
                                            weatherData = weatherData,
                                            modifier = Modifier
                                                .height(100.dp)
                                                .padding(horizontal = 16.dp)
                                        )
                                    }
                                }
                                //Current hour minute is following 30
                                else{
                                    if(weatherData.time >= LocalDateTime.now().plusHours(1)){
                                        HourlyWeatherDisplay(
                                            weatherData = weatherData,
                                            modifier = Modifier
                                                .height(100.dp)
                                                .padding(horizontal = 16.dp)
                                        )
                                    }
                                }
                            }
                            //current day hour is 20:00h and following
                            else{
                                if(weatherData.time >= LocalDateTime.now().minusHours(3)){
                                    HourlyWeatherDisplay(
                                        weatherData = weatherData,
                                        modifier = Modifier
                                            .height(100.dp)
                                            .padding(horizontal = 16.dp)
                                    )
                                }
                            }
                        }
                    })
                }
            }
        }
    }
}