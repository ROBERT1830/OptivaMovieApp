package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Award(
    @Json(name = "title")
    val title: String,
    @Json(name = "year")
    val year: Int
)