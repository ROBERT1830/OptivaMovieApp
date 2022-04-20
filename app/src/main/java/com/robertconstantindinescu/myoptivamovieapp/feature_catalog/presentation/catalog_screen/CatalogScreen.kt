package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.LocalSpacing
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.shared_components.TrackableMovieItem
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.MOVIE_ITEM_HEIGHT

@Composable
fun CatalogScreen(
    //navController: NavController,
    onNavigateToDetails: (id: String) -> Unit = {},
    viewModel: CatalogScreenViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(spacing.spaceMedium)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.trackableMovies){ movie ->
                TrackableMovieItem(
                    modifier = Modifier.height(MOVIE_ITEM_HEIGHT),
                    trackableMovie = movie,
                    onTrackableMovieClick = {
                        onNavigateToDetails(movie.externalId)
                    },
                    onFavoriteToggle = {
                        viewModel.onEvent(CatalogScreenEvent.OnTrackMovieClick(movie = movie))
                    }
                )

            }
        }

    }





}