package com.underdoggameworks.easyweather.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.LocalDate

@Composable
fun WeatherWeekForecast(
    state: WeatherState,
    navController: NavController,
    modifier: Modifier = Modifier
){
    state.weatherInfo?.weatherDataDaily?.get(0)?.let {data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.background(Color.Transparent)) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.weight(0.2f)
                ) {
                    Button(
                        onClick = { navController.popBackStack() },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = ThemeColors.button, contentColor = Color.White),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 2.dp,
                            pressedElevation = 8.dp,
                            disabledElevation = 0.dp,
                            hoveredElevation = 4.dp,
                            focusedElevation = 4.dp),
                        modifier = Modifier
                            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = Color.White
                        )
                    }
                }
                Text(
                    text = "Week Forecast",
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .weight(0.6f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier.weight(0.2f)
                ) {}
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(content = {
                items(data){weatherData ->
                    if(weatherData.date >= LocalDate.now().plusDays(1)){
                        WeeklyWeatherDisplay(
                            weatherData = weatherData,
                            modifier = Modifier
                                .height(75.dp)
                        )
                    }
                }
            })
        }
    }
}