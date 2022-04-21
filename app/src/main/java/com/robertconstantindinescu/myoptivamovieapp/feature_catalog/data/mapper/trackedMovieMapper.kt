package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.mapper

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.local.entities.FavoriteMovieEntity
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie

// TODO: GENERATE THE MAPPER
fun FavoriteMovieEntity.toTrackedMovie(): TrackedMovie{
    return TrackedMovie(
        id = this.id,
        name = this.name,
        year = this.year,
        contentProvider = this.contentProvider,
        attachments = this.attachments,
        externalId = this.externalId,
        description = this.description
    )

//    id = this.id,
//    externalId = externalId,
//    //result = this.result,
//    name = this.name
}

fun TrackedMovie.toFavoriteMovieEntity(): FavoriteMovieEntity{
    return FavoriteMovieEntity(
        id = this.id,
        name = this.name,
        year = this.year,
        contentProvider = this.contentProvider,
        attachments = this.attachments,
        externalId = this.externalId,
        description = this.description?:""
    )
}