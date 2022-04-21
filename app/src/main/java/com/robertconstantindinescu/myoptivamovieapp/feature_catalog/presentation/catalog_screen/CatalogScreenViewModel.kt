package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.SingleUiEvent
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.UiText
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case.CatalogUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel @Inject constructor(
    private val useCases: CatalogUseCases
): ViewModel() {

    var state by mutableStateOf(CatalogScreenState())
        private set

    private val _singleUiEvent = Channel<SingleUiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()

    init {
        executeSearch()
    }

    fun onEvent(event: CatalogScreenEvent){
        when(event){
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
        }
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
                    _singleUiEvent.send(SingleUiEvent.ShowSnackBar(
                        UiText.StringResource(R.string.generic_error)
                    ))

                }
        }
    }

    private fun trackMovie(trackableMovie: TrackableMovie) {
        viewModelScope.launch {
            useCases.trackMovie(trackableMovie = trackableMovie)
            state = state.copy(

                trackableMovies = state.trackableMovies.map {
                    if (it.name == trackableMovie.name){
                        it.copy(
                            isSavedToFav = !it.isSavedToFav!!
                        )
                    }else it
                }

                //isFavoriteToggle = !state.isFavoriteToggle
            )
            _singleUiEvent.send(SingleUiEvent.ShowSnackBar(
                UiText.StringResource(R.string.saved_to_favorites)
            ))
        }

    }

    private fun deleteTrackedMovie(trackableMovie: TrackableMovie) {
        viewModelScope.launch {
            useCases.deleteTrackedMovie(trackableMovie = trackableMovie)
            state = state.copy(
                trackableMovies = state.trackableMovies.map {
                    if (it.name == trackableMovie.name){
                        it.copy(isSavedToFav = !it.isSavedToFav!!)
                    }else it
                }
                //isFavoriteToggle = false
            )
        }

    }

}
