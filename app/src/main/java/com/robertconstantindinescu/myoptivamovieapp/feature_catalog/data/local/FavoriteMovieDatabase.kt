package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.local.entities.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class FavoriteMovieDatabase: RoomDatabase() {
    abstract val dao: FavoritesMovieDao
}