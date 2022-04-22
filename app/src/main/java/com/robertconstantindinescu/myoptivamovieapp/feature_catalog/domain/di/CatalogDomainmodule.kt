package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.di

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.repository.MovieRepository
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CatalogDomainModule {

    @ViewModelScoped
    @Provides
    fun provideCatalogUseCases(
        repository: MovieRepository
    ): CatalogUseCases{
        return CatalogUseCases(
            searchMovies = SearchMovies(repository),
            trackMovie = TrackMovie(repository),
            deleteFavoriteMovieFromCatalogScreen = DeleteFavoriteMovieFromCatalogScreen(repository),
            getTrackedMovies = GetTrackedMovies(repository),
            searchMovie = SearchMovie(repository),
            deleteFavoriteMovieFromFavScreen = DeleteFavoriteMovieFromFavScreen(repository),
            getMovieImage = GetMovieImage(),
            restoreFavoriteMovie = RestoreFavoriteMovie(repository)
        )
    }
}
