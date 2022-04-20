package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.repository.MovieRepository

class SearchMovies(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(): Result<List<TrackableMovie>>{
        return repository.searchMovie()
    }
}