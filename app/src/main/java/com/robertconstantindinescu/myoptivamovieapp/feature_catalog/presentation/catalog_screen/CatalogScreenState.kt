package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie

data class CatalogScreenState(
    val trackableMovies: List<TrackableMovie> = emptyList(),
    val trackedMovies: List<TrackedMovie> = emptyList(),
    val isFavoriteToggle: Boolean = false,
    val isSearching: Boolean = false,
    val movieImage: String ? = null
)
