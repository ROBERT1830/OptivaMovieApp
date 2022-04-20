package com.robertconstantindinescu.myoptivamovieapp.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen.CatalogScreen
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen.DetailsScreen
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.favorites_screen.FavoritesScreen
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.shared_components.BottomNavMenu
import com.robertconstantindinescu.myoptivamovieapp.navigation.util.Constants.DETAILS_ARGUMENT_KEY
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.MyOptivaMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyOptivaMovieAppTheme {

                //Keep track of the navigation
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val showBottomMenu = navBackStackEntry?.destination?.route in listOf(
                    BottomMenuScreen.Catalog.route,
                    BottomMenuScreen.Favorites.route
                )
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
                            if (showBottomMenu){
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
                            }

                        }) {
                        NavHost(
                            navController = navController,
                            startDestination = BottomMenuScreen.Catalog.route
                        ) {
                            composable(route = BottomMenuScreen.Catalog.route) {
                                CatalogScreen(
                                    onNavigateToDetails = {externalMovieId ->
                                        navController.navigate(
                                            Screen.DetailsScreen.route + "/$externalMovieId"
                                        )
                                    }
                                )
                            }

                            composable(
                                route = Screen.DetailsScreen.route+"/{$DETAILS_ARGUMENT_KEY}",
                                arguments = listOf(
                                    navArgument(DETAILS_ARGUMENT_KEY){
                                        type = NavType.StringType
                                    }
                                )
                            ) {
                                val externalMovieId = it.arguments?.getString(DETAILS_ARGUMENT_KEY)!!

                                DetailsScreen(
                                    scrollState = scrollState,
                                    scaffoldState = scaffoldState,
                                    externalMovieId = externalMovieId,
                                    onNavigateUp = {
                                        navController.navigateUp()
                                    }
                                )
                            }
                            composable(route = BottomMenuScreen.Favorites.route) {
                                FavoritesScreen(navController = navController)
                            }
                        }
                    }


                }
            }
        }
    }
}

