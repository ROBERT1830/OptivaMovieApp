package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.ExtendedTrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie

data class DetailsScreenState(
    val isLoading: Boolean = false,
    val extendedTrackableMovie: ExtendedTrackableMovie? = null


    /**
     * TrackableMovie(
    name = null,
    year = null,
    contentProvider = null,
    attachments = emptyList(),
    externalId = null,
    description = null
    )
     */
)