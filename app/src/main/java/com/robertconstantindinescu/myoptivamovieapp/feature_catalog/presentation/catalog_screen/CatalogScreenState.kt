package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie

data class CatalogScreenState(
    val trackableMovies: List<TrackableMovie> = emptyList(),
    val isFavoriteToggle: Boolean = false,
    val isSearching: Boolean = false
)
