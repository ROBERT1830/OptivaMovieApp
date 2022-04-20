package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.mapper

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.Response
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie

fun Response.toTrackableMovie(): TrackableMovie {
    return TrackableMovie(
        name = this.name,
        year = this.year,
        contentProvider = this.contentProvider,
        attachments = this.attachments,
        externalId = this.externalId
    )
}