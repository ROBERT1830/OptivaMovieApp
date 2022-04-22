package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.repository.MovieRepository

class RestoreFavoriteMovie(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(trackedMovie: TrackedMovie){
        repository.insertTrackedMovie(trackedMovie)
    }
}