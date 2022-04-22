package com.robertconstantindinescu.myoptivamovieapp.navigation

import com.robertconstantindinescu.myoptivamovieapp.navigation.util.Constants.DETAILS_SCREEN
import com.robertconstantindinescu.myoptivamovieapp.navigation.util.Constants.SPLASH_SCREEN

sealed class Screen(val route:String){
    object DetailsScreen: Screen(DETAILS_SCREEN)
    object SplashScreen: Screen(SPLASH_SCREEN)
}