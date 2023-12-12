package com.underdoggameworks.easyweather.presentation

import android.location.Geocoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.underdoggameworks.easyweather.R
import com.underdoggameworks.easyweather.domain.weather.WeatherData
import com.underdoggameworks.easyweather.navigation.Geolocation
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@ExperimentalMaterial3Api
@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    geocoder: Geocoder,
    viewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {
    //TIME
    val weeklyDate : String = LocalDate.now().dayOfWeek.name.lowercase().replaceFirstChar(Char::titlecase)

    state.weatherInfo?.currentWeatherData?.let { data ->
        Box{
            Card(
                backgroundColor = backgroundColor,
                shape = RoundedCornerShape(10.dp),
                modifier = modifier.padding(horizontal = 16.dp).align(Alignment.Center),
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Text(
                            text = Geolocation().getAddressLocation(geocoder = geocoder, viewModel = viewModel),
                            color = Color.White,
                            fontSize = 19.sp
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "$weeklyDate ${
                                data.time.format(
                                    DateTimeFormatter.ofPattern("HH:mm")
                                )
                            }",
                            color = Color.White,
                            fontSize = 19.sp
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = painterResource(id = data.weatherType.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(200.dp)
                        )
                        Text(
                            text = "${data.temperatureCelsius}°C",
                            color = Color.White,
                            fontSize = 50.sp
                        )
                        Text(
                            text = data.weatherType.weatherDesc,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                    WeatherInfo(data = data)
                }
            }
        }
    }
}

@Composable
fun WeatherInfo(data: WeatherData){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(5f)
            ){
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                    contentDescription = "Pressure",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(30.dp)
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 8.dp).width(100.dp)
                ) {
                    Text(
                        text = "${data.pressure.roundToInt()} hPa",
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = "Pressure",
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(5f)
            ){
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_rainy),
                    contentDescription = "Precipitation",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(30.dp)
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 8.dp).width(100.dp)
                ) {
                    Text(
                        text = "${data.precipitationPrb.roundToInt()} %",
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = "Precipitation",
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(5f)
            ){
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_wind),
                    contentDescription = "Wind Speed",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(30.dp)
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 8.dp).width(100.dp)
                ) {
                    Text(
                        text = "${data.windSpeed.roundToInt()} km/h",
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = "Wind Speed",
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(5f)
            ){
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_drop),
                    contentDescription = "Humidity",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(30.dp)
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 8.dp).width(100.dp)
                ) {
                    Text(
                        text = "${data.humidity.roundToInt()} %",
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = "Humidity",
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}