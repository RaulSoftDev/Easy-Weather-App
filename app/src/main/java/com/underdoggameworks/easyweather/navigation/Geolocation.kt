package com.underdoggameworks.easyweather.navigation

import android.location.Geocoder
import androidx.compose.material3.ExperimentalMaterial3Api
import com.underdoggameworks.easyweather.presentation.WeatherViewModel

class Geolocation() {

    @Suppress("DEPRECATION")
    fun getAddressLocation(geocoder: Geocoder, viewModel: WeatherViewModel): String {
        println("Location on SDK < 33 - Compatibility Mode")
        val addresses = geocoder.getFromLocation(viewModel.latitud, viewModel.longitude, 1)
        // For Android SDK < 33, the addresses list will be still obtained from the getFromLocation() method
        if (addresses != null) {
            println("${viewModel.latitud} / ${viewModel.longitude}")
            if(addresses[0].locality != null) return addresses[0].locality
            else return addresses[0].adminArea
        } else return "No Info"
    }

    @ExperimentalMaterial3Api
    @Suppress("DEPRECATION")
    fun getAddressCoordinates(location: String, geocoder: Geocoder): MutableList<Double> {
        val geocoder: Geocoder = geocoder
        val coordinates: MutableList<Double> = mutableListOf()

        val addresses = geocoder.getFromLocationName(location, 1)

        if (addresses != null) {
            if(addresses.isNotEmpty()){
                addresses?.get(0)?.let {
                    println("Long ${it.longitude} / Lat ${it.latitude}")
                    coordinates.clear()
                    coordinates.add(it.latitude)
                    coordinates.add(it.longitude)
                }

                return coordinates
            }
        }

        return coordinates
    }
}