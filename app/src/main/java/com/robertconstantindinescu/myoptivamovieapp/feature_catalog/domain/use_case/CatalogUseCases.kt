package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case

data class CatalogUseCases(
    val searchMovies: SearchMovies,
    val trackMovie: TrackMovie,
    val deleteFavoriteMovieFromCatalogScreen: DeleteFavoriteMovieFromCatalogScreen,
    val getTrackedMovies: GetTrackedMovies,
    val searchMovie: SearchMovie,
    val deleteFavoriteMovieFromFavScreen: DeleteFavoriteMovieFromFavScreen,
    val getMovieImage: GetMovieImage
)
