package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.favorites_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.LocalSpacing
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.SingleUiEvent
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen.CatalogScreenEvent
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.favorites_screen.components.FavoriteMovieItem
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.shared_components.TrackableMovieItem
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.MOVIE_ITEM_HEIGHT
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoritesScreen(
    scaffoldState: ScaffoldState,
    onNavigationToDetails: (movieId: String) -> Unit = {},
    viewModel: FavoritesScreenViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val state = viewModel.state

    val context = LocalContext.current

    //SINGLE UI EVENTS
    LaunchedEffect(key1 = true){
        viewModel.singleUiEvent.collectLatest { event ->
            when(event){
                is SingleUiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> {}
            }

        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(bottom = 50.dp))){
            items(state.favoriteMoviesList){ movie ->
                FavoriteMovieItem(
                    modifier = Modifier
                        .height(MOVIE_ITEM_HEIGHT)
                        .padding(spacing.spaceMedium),
                    trackedMovie =  movie,
                    onFavoriteMovieClick = {
                        onNavigationToDetails(movie.externalId)
                    },
                    onDeleteFavoriteMovieClick = {
                        viewModel.onEvent(FavoritesScreenEvent.OnDeleteFavoriteMovie(movie = movie))
                    }
                )

            }
        }

    }

}




























