package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.local.entities.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow


interface FavoritesMovieDao {

    @Query("SELECT * FROM favorite_movie_table")
    fun getMovies(): Flow<List<FavoriteMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Delete
    suspend fun deleteMovie(favoriteMovieEntity: FavoriteMovieEntity)
}