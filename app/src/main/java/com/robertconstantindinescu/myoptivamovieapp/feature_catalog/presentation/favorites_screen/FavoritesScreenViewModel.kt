package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.favorites_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.SingleUiEvent
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.UiText
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case.CatalogUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val useCases: CatalogUseCases
) : ViewModel() {

    var state by mutableStateOf(FavoritesScreenState())
        private set

    private val _singleUiEvent = Channel<SingleUiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()

    private var getFavoriteMovies: Job? = null

    init {
        refreshFavoriteMovies()
    }

    fun onEvent(event: FavoritesScreenEvent) {
        when (event) {
            is FavoritesScreenEvent.OnDeleteFavoriteMovie -> {
                deleteFavoriteMovie(event.movie)
            }
        }
    }

    private fun deleteFavoriteMovie(movie: TrackedMovie) {
        viewModelScope.launch {
            useCases.deleteFavoriteMovieFromFavScreen(movie = movie)
            refreshFavoriteMovies()
            _singleUiEvent
                .send(
                    SingleUiEvent.ShowSnackBar(
                        UiText.StringResource(R.string.favorite_movie_deleted)
                    )
                )
        }
    }

    private fun refreshFavoriteMovies() {
        getFavoriteMovies?.cancel()
        getFavoriteMovies = useCases.getTrackedMovies.invoke().map {
            state = state.copy(favoriteMoviesList = it)
        }.launchIn(viewModelScope)
    }


}