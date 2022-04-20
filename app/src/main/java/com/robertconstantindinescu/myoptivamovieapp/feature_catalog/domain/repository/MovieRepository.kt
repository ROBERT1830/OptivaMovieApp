package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.repository

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun searchMovie(): Result<List<TrackableMovie>>

    // TODO: implement functions for local

    suspend fun insertTrackedMovie(movie: TrackedMovie)
    suspend fun deleteTrackedMovie(movie: TrackedMovie)
    fun getTrackedMovies(): Flow<List<TrackedMovie>>

}