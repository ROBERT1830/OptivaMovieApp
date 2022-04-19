package com.robertconstantindinescu.myoptivamovieapp.navigation

import com.robertconstantindinescu.myoptivamovieapp.navigation.util.Constants.DETAILS_SCREEN

sealed class Screen(val route:String){
    object DetailsScreen: Screen(DETAILS_SCREEN)
}