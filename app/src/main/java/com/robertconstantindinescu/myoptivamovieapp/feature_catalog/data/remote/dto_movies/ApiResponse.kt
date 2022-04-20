package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ApiResponse(
//    @Json(name = "metadata")
//    val metadata: Metadata,
    @Json(name = "response")
    val response: List<Response>
): Parcelable