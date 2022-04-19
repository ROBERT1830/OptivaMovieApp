package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
//Filter by name to get the image path
@JsonClass(generateAdapter = true)
data class Attachment(
    @Json(name = "name")
    val name: String,
    @Json(name = "value")
    val value: String
)