package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


//Filter by name to get the image path
@JsonClass(generateAdapter = true)
@Parcelize
data class Attachment(
    @Json(name = "name")
    val name: String,
    @Json(name = "value")
    val value: String
):Parcelable