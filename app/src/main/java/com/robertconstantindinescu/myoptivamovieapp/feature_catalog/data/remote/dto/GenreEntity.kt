package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreEntity(
//    @Json(name = "externalId")
//    val externalId: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
//    @Json(name = "parentName")
//    val parentName: String,
//    @Json(name = "responseElementType")
//    val responseElementType: String
)