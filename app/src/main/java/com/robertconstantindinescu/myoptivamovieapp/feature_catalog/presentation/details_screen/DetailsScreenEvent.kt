package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen

sealed class DetailsScreenEvent{
    data class SearchMovie(val movieId: String): DetailsScreenEvent()
}
