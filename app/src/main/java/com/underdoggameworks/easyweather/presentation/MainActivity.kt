package com.underdoggameworks.easyweather.presentation

import android.Manifest
import android.graphics.Color
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.underdoggameworks.easyweather.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    lateinit var geocoder : Geocoder
    lateinit var statusColor: Color

    override fun onCreate(savedInstanceState: Bundle?) {
        println(Build.VERSION.SDK_INT)

        geocoder = Geocoder(this, Locale.getDefault())

        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))

        setContent {
            AppNavigation(viewModel = viewModel, geocoder = geocoder)
        }

        //Status Bar Color SetUp to be integrated in new Android API
        /*window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = ThemeColors.button.hashCode();*/
    }
}