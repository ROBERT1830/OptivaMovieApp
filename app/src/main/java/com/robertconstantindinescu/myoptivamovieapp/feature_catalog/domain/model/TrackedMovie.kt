package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.Response

data class TrackedMovie(
    var id: Int,
    var externalId: String,
    var result: Response,
    var name: String,
)
