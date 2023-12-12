package com.underdoggameworks.easyweather.navigation

sealed class AppScreens(val route: String){
    object MainScreen: AppScreens("main_screen")
    object WeekScreen: AppScreens("week_screen")
}
