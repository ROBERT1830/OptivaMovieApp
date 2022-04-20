package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.ExtendedTrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.repository.MovieRepository

class SearchMovie(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: String): Result<ExtendedTrackableMovie>{
       return repository.searchMovie(movieId)
    }
}