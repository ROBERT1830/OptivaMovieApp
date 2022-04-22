package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.favorites_screen

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie

sealed class FavoritesScreenEvent {
    //object GetFavoriteMovies: FavoritesScreenEvent()
    data class OnDeleteFavoriteMovie(val movie: TrackedMovie): FavoritesScreenEvent()
    object OnRestoreFavoriteMovie: FavoritesScreenEvent()

}