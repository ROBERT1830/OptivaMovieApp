package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
//    @Json(name = "metadata")
//    val metadata: Metadata,
    @Json(name = "response")
    val response: List<Response>
)