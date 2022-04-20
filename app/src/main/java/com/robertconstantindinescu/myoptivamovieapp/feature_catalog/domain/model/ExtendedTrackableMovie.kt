package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies.Attachment
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies.Award
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies.GenreEntity
import com.squareup.moshi.Json
import kotlinx.parcelize.RawValue

data class ExtendedTrackableMovie(

    val attachments: List<Attachment>,
    val awards:  List<Award>? = null,
    val contentProvider: String,
    val definition: String,
    val description: String,
    val duration: Int,
    val externalId: String,
    val genreEntityList: List<GenreEntity>,
    val id: Int,
    val name: String,
    val type: String,
    val year: Int
)
