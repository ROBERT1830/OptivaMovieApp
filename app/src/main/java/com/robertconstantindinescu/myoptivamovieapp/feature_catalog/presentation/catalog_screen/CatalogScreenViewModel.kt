package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.SingleUiEvent
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.UiText
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case.CatalogUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel @Inject constructor(
    private val useCases: CatalogUseCases
) : ViewModel() {

    var state by mutableStateOf(CatalogScreenState())
        private set

    private val _singleUiEvent = Channel<SingleUiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()

    private var getFavoriteMovies: Job? = null

    init {
        executeSearch()
        getFavoriteMovies()

    }


    fun onEvent(event: CatalogScreenEvent) {
        when (event) {
            //in case text field search is implemented
            is CatalogScreenEvent.SearchMovies -> {
                executeSearch()
            }
            //Add to favorites
            is CatalogScreenEvent.OnTrackMovieClick -> {

                trackMovie(event.movie)
            }
            is CatalogScreenEvent.OnDeleteTrackedMovieClick -> {
                deleteTrackedMovie(event.movie)
            }
            is CatalogScreenEvent.GetMovieImage -> {
                getMovieImage(event.movie)
            }
            is CatalogScreenEvent.CheckFavoriteMovieDatabase -> {
                viewModelScope.launch {
                    delay(500)
                    checkForFavoriteMovies()
                }
            }

        }
    }

    private fun checkForFavoriteMovies() {
        Log.d("CHECK_PROCESS", "--> check_called")
        if (state.trackedMovies.isEmpty()) {
            state = state.copy(
                trackableMovies = state.trackableMovies.map {
                    it.copy(
                        isSavedToFav = false
                    )
                }
            )
        }
        val list: MutableList<TrackableMovie> = mutableListOf()
        state.trackedMovies.forEachIndexed { index, trackedMovie ->
            val movieFinded = state.trackableMovies.findLast {
                it.id == trackedMovie.id

            }
            movieFinded?.let {
                list.add(movieFinded)
            }


        }
        state = state.copy(
            trackableMovies = state.trackableMovies.map {
                if (it in list) {
                    it.copy(
                        isSavedToFav = true
                    )
                } else it.copy(
                    isSavedToFav = false
                )

            }
        )
    }

    private fun getFavoriteMovies() {
        Log.d("CHECK_PROCESS", "--> favorites_called")
        getFavoriteMovies?.cancel()
        getFavoriteMovies = useCases.getTrackedMovies.invoke().map {
            state = state.copy(trackedMovies = it)
        }.launchIn(viewModelScope)

    }

    private fun getMovieImage(trackableMovie: TrackableMovie) {

        val movieImage = useCases.getMovieImage(trackableMovie = trackableMovie)
        state = state.copy(movieImage = movieImage)

//        state.trackableMovies.map {
//            if (it.name == trackableMovie.name) {
//                it.copy(imageUrl = movieImage)
//            } else it
//        }


    }

    private fun executeSearch() {

        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                //refresh
                trackableMovies = emptyList()
            )
            useCases.searchMovies()
                .onSuccess { movies ->
                    state = state.copy(
                        trackableMovies = movies,
                        isSearching = false
                    )
                }
                .onFailure {
                    state = state.copy(
                        isSearching = false
                    )
                    _singleUiEvent.send(
                        SingleUiEvent.ShowSnackBar(
                            UiText.StringResource(R.string.generic_error)
                        )
                    )

                }
        }
    }

    private fun trackMovie(trackableMovie: TrackableMovie) {
        viewModelScope.launch {
            useCases.trackMovie(trackableMovie = trackableMovie)
            state = state.copy(

                trackableMovies = state.trackableMovies.map {
                    if (it.name == trackableMovie.name) {
                        it.copy(
                            isSavedToFav = !it.isSavedToFav!!
                        )
                    } else it
                }
            )
            _singleUiEvent.send(
                SingleUiEvent.ShowSnackBar(
                    UiText.StringResource(R.string.saved_to_favorites)
                )
            )
        }

    }

    private fun deleteTrackedMovie(trackableMovie: TrackableMovie) {
        viewModelScope.launch {
            useCases.deleteFavoriteMovieFromCatalogScreen(trackableMovie = trackableMovie)
            state = state.copy(
                trackableMovies = state.trackableMovies.map {
                    if (it.name == trackableMovie.name) {
                        it.copy(isSavedToFav = !it.isSavedToFav!!)
                    } else it
                }
            )
            _singleUiEvent.send(
                SingleUiEvent.ShowSnackBar(
                    UiText.StringResource(R.string.delete_from_favorites)
                )
            )
        }

    }

}
