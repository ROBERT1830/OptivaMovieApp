package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.favorites_screen

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie

data class FavoritesScreenState(
    val favoriteMoviesList: List<TrackedMovie> = listOf()
)
