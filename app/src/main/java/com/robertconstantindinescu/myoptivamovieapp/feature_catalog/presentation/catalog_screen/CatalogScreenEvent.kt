package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie

sealed class CatalogScreenEvent{
    object SearchMovies: CatalogScreenEvent()
    object CheckFavoriteMovieDatabase: CatalogScreenEvent()
    data class OnTrackMovieClick(
        val movie: TrackableMovie
    ):CatalogScreenEvent()
    data class OnDeleteTrackedMovieClick(val movie: TrackableMovie): CatalogScreenEvent()
    data class GetMovieImage(val movie: TrackableMovie): CatalogScreenEvent()
}
