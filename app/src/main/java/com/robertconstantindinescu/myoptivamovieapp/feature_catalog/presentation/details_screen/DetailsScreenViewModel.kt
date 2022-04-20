package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.SingleUiEvent
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.UiText
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case.CatalogUseCases
import com.robertconstantindinescu.myoptivamovieapp.navigation.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val useCases: CatalogUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var state by mutableStateOf(DetailsScreenState())
        private set

    private val _singleUiEvent = Channel<SingleUiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            val movieId = savedStateHandle.get<String>(DETAILS_ARGUMENT_KEY)
            movieId?.let {
                executeSearch(it)
            }

        }

    }

    private suspend fun executeSearch(movieId: String) {

        state = state.copy(
            isLoading = true,
            extendedTrackableMovie = null
        )
        useCases.searchMovie(movieId)
            .onSuccess { extendedMovie ->
                state = state.copy(
                    isLoading = false,
                    extendedTrackableMovie = extendedMovie
                )
            }
            .onFailure {
                state = state.copy(
                    isLoading = false
                )
                _singleUiEvent.send(SingleUiEvent.ShowSnackBar(
                    UiText.StringResource(R.string.generic_error)
                ))
            }
        //Log.d("MOVIE_DETAILS", movieeeee.toString())
    }
}


































