package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "attachments")
    val attachments: List<Attachment>,
    @Json(name = "awards")
    val awards: List<Award>,
    @Json(name = "contentProvider")
    val contentProvider: String,
    @Json(name = "definition")
    val definition: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "duration")
    val duration: Int,
    @Json(name = "externalId")
    val externalId: String,
    @Json(name = "genreEntityList")
    val genreEntityList: List<GenreEntity>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "year")
    val year: Int
)