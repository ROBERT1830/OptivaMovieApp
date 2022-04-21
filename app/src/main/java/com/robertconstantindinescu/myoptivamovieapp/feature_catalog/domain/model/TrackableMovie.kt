package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies.Attachment

data class TrackableMovie(
    val id: Int? = null,
    val name: String ? = null,
    val year: Int? = null,
    val contentProvider: String? = null,
    val attachments: List<Attachment> = listOf(),
    val externalId: String? = null,
    val description: String? = null,

    val isSavedToFav: Boolean? = false,
    val imageUrl: String? = null
    //val response: Response
)