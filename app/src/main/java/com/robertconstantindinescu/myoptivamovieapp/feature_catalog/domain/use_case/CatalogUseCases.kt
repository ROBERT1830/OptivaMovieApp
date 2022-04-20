package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case

data class CatalogUseCases(
    val searchMovies: SearchMovies,
    val trackMovie: TrackMovie,
    val deleteTrackedMovie: DeleteTrackedMovie,
    val getTrackedMovies: GetTrackedMovies
)
