package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Award(
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "year")
    val year: Int? = null
): Parcelable