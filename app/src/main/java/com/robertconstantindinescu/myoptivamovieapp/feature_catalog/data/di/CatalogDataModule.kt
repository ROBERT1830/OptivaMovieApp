package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.di

import android.app.Application
import androidx.room.Room
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.local.FavoriteMovieDatabase
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.MovieApi
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.repository.MovieRepositoryImpl
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.util.Constants.BASE_URL
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.util.Constants.DATABASE_NAME
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CatalogDataModule {

    //API

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    //LOCAL

    @Provides
    @Singleton
    fun provideFavoriteMovieDatabase(app: Application): FavoriteMovieDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteMovieDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieApi,
        db: FavoriteMovieDatabase
    ): MovieRepository{
        return MovieRepositoryImpl(
            dao = db.dao,
            api = api
        )
    }




}