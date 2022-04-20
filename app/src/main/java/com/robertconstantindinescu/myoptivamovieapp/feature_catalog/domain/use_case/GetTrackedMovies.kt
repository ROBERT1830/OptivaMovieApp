package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetTrackedMovies(
    private val repository: MovieRepository
) {

    operator fun invoke(): Flow<List<TrackedMovie>>{
        return repository.getTrackedMovies()
    }
}