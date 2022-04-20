package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.Attachment
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.Response

data class TrackedMovie(
    var id: Int? = null,
//    var externalId: String,
//    var result: Response,
//    var name: String,
    val name: String,
    val year: Int,
    val contentProvider: String,
    val attachments: List<Attachment>,
    val externalId: String,
)
