package com.underdoggameworks.easyweather.presentation

import android.location.Geocoder
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.underdoggameworks.easyweather.presentation.ui.theme.WeatherAppTheme

lateinit var backgroundColor: Brush

@ExperimentalMaterial3Api
@Composable
fun MainScreen(navController: NavController, viewModel: WeatherViewModel, geocoder: Geocoder){
    viewModel.state.weatherInfo?.currentWeatherData?.let {data ->
        backgroundColor = weatherBackgroundColor(data.weatherCode)
        WeatherAppTheme {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                ) {
                    //Search Bar
                    SearchLocationBar(viewModel = viewModel, geocoder = geocoder)
                    //Today Weather Info
                    WeatherCard(
                        state = viewModel.state,
                        backgroundColor = Color.Transparent,
                        geocoder = geocoder,
                        viewModel = viewModel,
                        //modifier = Modifier.weight(6.5f, true)
                    )
                    //Today Weather in Hours
                    WeatherForecast(
                        state = viewModel.state,
                        navController = navController,
                        //modifier = Modifier.weight(3.5f, true)
                    )
                    //Spacer(modifier = Modifier.height(16.dp))
                }
                if(viewModel.state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                viewModel.state.error?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

//Background Color
fun weatherBackgroundColor(code: Int): Brush{
    println(code)
    return when(code){
        //Clear sky
        in 0 .. 1 -> Brush.verticalGradient(listOf(
            ThemeColors.clearTop,
            ThemeColors.clearMain,
            ThemeColors.clearBase
        ))
        //Cloudy
        in 2..3 -> Brush.verticalGradient(listOf(
            ThemeColors.cloudTop,
            ThemeColors.cloudMain,
            ThemeColors.cloudBase
        ))
        in 45 .. 48 -> Brush.verticalGradient(listOf(
            ThemeColors.cloudTop,
            ThemeColors.cloudMain,
            ThemeColors.cloudBase
        ))
        //Snowy
        in 66 .. 77 -> Brush.verticalGradient(listOf(
            ThemeColors.snowTop,
            ThemeColors.snowMain,
            ThemeColors.snowBase
        ))
        //Rainy & Snowy
        else -> Brush.verticalGradient(listOf(
            ThemeColors.rainTop,
            ThemeColors.rainMain,
            ThemeColors.rainBase
        ))
    }
}
