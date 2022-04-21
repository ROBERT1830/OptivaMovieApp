package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.repository.MovieRepository

class DeleteTrackedMovie(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(trackableMovie: TrackableMovie){
        repository.deleteTrackedMovie(
            TrackedMovie(
                id = trackableMovie.id,
                name = trackableMovie.name?:"",
                year = trackableMovie.year?:-1,
                contentProvider = trackableMovie.contentProvider?:"",
                attachments = trackableMovie.attachments,
                externalId = trackableMovie.externalId?:""
            )

        )
    }


}