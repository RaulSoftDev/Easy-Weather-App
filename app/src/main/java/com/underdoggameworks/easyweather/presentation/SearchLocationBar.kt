package com.underdoggameworks.easyweather.presentation

import android.location.Geocoder
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.underdoggameworks.easyweather.navigation.Geolocation

//History Search Saver
val saver = Saver<SnapshotStateList<String>,MutableList<String>>(
    save = {it.toMutableList()},
    restore = {it.toMutableStateList()}
)

@ExperimentalMaterial3Api
@Composable
fun SearchLocationBar(
    viewModel: WeatherViewModel,
    geocoder: Geocoder
){
    //History Search Data
    var text by remember{ mutableStateOf("") }
    var active by remember{ mutableStateOf(false) }
    val currentList = rememberSaveable(saver = saver){ mutableStateListOf("GPS Location") }

    //Warning Context
    val context = LocalContext.current

    //Customisation
    var iconColor: Color = Color.Black

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        query = text,
        onQueryChange = {text = it},
        onSearch = {
            if(text.isNotEmpty()){
                //Reload app info
                val newLocation = Geolocation().getAddressCoordinates(location = it, geocoder = geocoder)
                if(newLocation.isNotEmpty()){
                    currentList.add(it)
                    viewModel.loadCustomWeatherInfo(newLocation[0], newLocation[1])
                    println(viewModel.state.weatherInfo?.currentWeatherData?.weatherType?.weatherDesc)
                }
                else{
                    Toast.makeText(context, "Location not found, try again", Toast.LENGTH_SHORT).show()
                }
                //Clean search bar
                text = ""
            }
            active = false
        },
        active = active,
        onActiveChange = {active = it},
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            if(active){
                Icon(
                    modifier = Modifier.clickable {if(text.isNotEmpty()) text = ""
                    else active = false},
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear Icon"
                )
            }
        }
    ) {
        currentList.forEach {
            var enabled : Boolean = false

            if(it == currentList[0]){
                iconColor = Color.Transparent
                enabled = false
            }
            else{
                iconColor = Color.Black
                enabled = true
            }

            Row(
                modifier = Modifier
                    .padding(18.dp)
                    .clickable {
                        if (it == currentList[0]) {
                            viewModel.loadWeatherInfo()
                            active = false
                        } else {
                            val newLocation = Geolocation().getAddressCoordinates(
                                location = it,
                                geocoder = geocoder
                            )
                            viewModel.loadCustomWeatherInfo(newLocation[0], newLocation[1])
                            active = false
                        }
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .weight(1f, true),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon"
                )
                Text(text = it, modifier = Modifier.weight(8f, true))
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Icon",
                    modifier = Modifier
                        .weight(1f, true)
                        .clickable(enabled = enabled) {
                            if (it != currentList[0]) currentList.remove(
                                it
                            )
                        },
                    tint = iconColor
                )
            }
        }
    }
}