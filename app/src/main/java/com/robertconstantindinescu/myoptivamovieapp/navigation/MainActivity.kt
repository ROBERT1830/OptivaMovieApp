package com.robertconstantindinescu.myoptivamovieapp.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen.CatalogScreen
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen.DetailsScreen
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.favorites_screen.FavoritesScreen
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.shared_components.BottomNavMenu
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.MyOptivaMovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyOptivaMovieAppTheme {

                //Keep track of the navigation
                val navController = rememberNavController()
                //For showing toast messages in coroutineScope
                val scaffoldState = rememberScaffoldState()
                //For performing scroll in details
                val scrollState = rememberScrollState()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState,
                        bottomBar = {
                            BottomNavMenu(
                                navController = navController,
                                onBottomIconClick = { route ->
                                    navController.navigate(route){
                                        navController.graph.startDestinationRoute?.let { route ->
                                            popUpTo(route){
                                                saveState = true
                                            }
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }) {
                        NavHost(
                            navController = navController,
                            startDestination = BottomMenuScreen.Catalog.route
                        ) {
                            composable(route = BottomMenuScreen.Catalog.route) {
                                CatalogScreen(navController = navController)
                            }

                            composable(route = BottomMenuScreen.Favorites.route) {
                                FavoritesScreen(navController = navController)
                            }
                            composable(route = Screen.DetailsScreen.route) {
                                DetailsScreen(navController = navController)
                            }

                        }
                    }


                }
            }
        }
    }
}

