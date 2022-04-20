package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DetailsScreen(
    //navController: NavController
    scrollState: ScrollState,
    scaffoldState: ScaffoldState,
    externalMovieId: String,
    onNavigateUp: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Details Screen",
            style = MaterialTheme.typography.body1
        )
    }

}