package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies


import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies.Response
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponseExtended(
    @Json(name = "response")
    val response: Response
)