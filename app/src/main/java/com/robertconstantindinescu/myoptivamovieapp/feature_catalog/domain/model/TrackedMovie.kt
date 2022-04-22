package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies.Attachment

data class TrackedMovie(
    var id: Int? = null,
    val name: String,
    val year: Int,
    val contentProvider: String,
    val attachments: List<Attachment>,
    val externalId: String,
    val description: String?=null
)
