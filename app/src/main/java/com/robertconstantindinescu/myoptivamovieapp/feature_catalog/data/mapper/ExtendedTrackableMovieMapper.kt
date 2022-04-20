package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.mapper


import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies.Response
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.ExtendedTrackableMovie

fun Response.toExtendedTrackableMovie(): ExtendedTrackableMovie{
    return ExtendedTrackableMovie(
        attachments = this.attachments,
        awards = this.awards,
        contentProvider = this.contentProvider,
        description = this.description,
        definition = this.definition,
        duration = this.duration,
        externalId = this.externalId,
        genreEntityList = this.genreEntityList,
        id = this.id,
        name = this.name,
        type = this.type,
        year = this.year
    )
}