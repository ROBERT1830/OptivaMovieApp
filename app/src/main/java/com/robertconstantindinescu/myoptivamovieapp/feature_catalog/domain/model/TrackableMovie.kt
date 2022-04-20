package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies.Attachment

data class TrackableMovie(
    val name: String ? = null,
    val year: Int? = null,
    val contentProvider: String? = null,
    val attachments: List<Attachment>,
    val externalId: String? = null,
    val description: String? = null
    //val response: Response
)