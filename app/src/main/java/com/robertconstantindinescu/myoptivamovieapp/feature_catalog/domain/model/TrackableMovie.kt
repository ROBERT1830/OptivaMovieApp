package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.Attachment

data class TrackableMovie(
    val name: String,
    val year: Int,
    val contentProvider: String,
    val attachments: List<Attachment>,
    val externalId: String
)