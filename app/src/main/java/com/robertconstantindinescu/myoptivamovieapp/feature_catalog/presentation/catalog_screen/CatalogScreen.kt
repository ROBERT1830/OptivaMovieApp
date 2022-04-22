package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.LocalSpacing
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.shared_components.TrackableMovieItem
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.MOVIE_ITEM_HEIGHT
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.SingleUiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CatalogScreen(
    scaffoldState: ScaffoldState,
    onNavigateToDetails: (id: String) -> Unit = {},
    viewModel: CatalogScreenViewModel = hiltViewModel()
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
    //Trigger again when database changes.
    LaunchedEffect(key1 = state.trackedMovies){
        viewModel.onEvent(CatalogScreenEvent.CheckFavoriteMovieDatabase)
    }

    //USER INTERFACE DATA
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(bottom = 50.dp))){
            items(state.trackableMovies){ movie ->
                TrackableMovieItem(
                    viewModel = viewModel,
                    modifier = Modifier
                        .height(MOVIE_ITEM_HEIGHT)
                        .padding(spacing.spaceMedium),
                    trackableMovie = movie,
                    onTrackableMovieClick = {
                        movie.externalId?.let { onNavigateToDetails(it) }
                    },
                    onFavoriteToggle = {
                        if (movie.isSavedToFav!!){
                            viewModel.onEvent(CatalogScreenEvent.OnDeleteTrackedMovieClick(movie = movie))
                        }else{
                            viewModel.onEvent(CatalogScreenEvent.OnTrackMovieClick(movie = movie))
                        }

                    },
                    isAddedToFav = movie.isSavedToFav!!
                )

            }
        }

    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        when{
            state.isSearching -> CircularProgressIndicator()
            state.trackableMovies.isEmpty() -> {
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}


















































