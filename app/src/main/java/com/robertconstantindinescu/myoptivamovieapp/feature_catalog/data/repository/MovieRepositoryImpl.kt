package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.repository

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.local.FavoritesMovieDao
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.mapper.toExtendedTrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.mapper.toFavoriteMovieEntity
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.mapper.toTrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.mapper.toTrackedMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.MovieApi
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.ExtendedTrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val dao: FavoritesMovieDao,
    private val api: MovieApi
) : MovieRepository {

    override suspend fun searchMovies(): Result<List<TrackableMovie>> {

        return try {
            val searchDtoMovies = api.searchMovies()
            Result.success(
                searchDtoMovies.response.map {
                    it.toTrackableMovie()
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun searchMovie(movieId: String): Result<ExtendedTrackableMovie> {
        return try {
            val searchDtoMovie = api.searchMovie(movieId)
            Result.success(
                searchDtoMovie.response.toExtendedTrackableMovie()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedMovie(movie: TrackedMovie) {
        dao.insertMovie(movie.toFavoriteMovieEntity())
    }

    override suspend fun deleteTrackedMovie(movie: TrackedMovie) {
        dao.deleteMovie(movie.toFavoriteMovieEntity())
    }

    override fun getTrackedMovies(): Flow<List<TrackedMovie>> {
        return dao.getMovies().map {
            it.map { favMovie ->
                favMovie.toTrackedMovie()
            }
        }
    }
}