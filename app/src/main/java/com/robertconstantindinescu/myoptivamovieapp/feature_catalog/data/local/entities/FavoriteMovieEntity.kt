package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto_movies.Attachment
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.util.Constants.FAVORITE_MOVIE_TABLE

@Entity(tableName = FAVORITE_MOVIE_TABLE)
data class FavoriteMovieEntity(
//    val externalId: String,
//    val year: Int,
//    val name: String,
//    val description: String,
//    val type:String,
//    //type of image - use for filter
//    val attachmentName: String,
//    //image url
//    val attachmentValue: String,
//    val contentProvider: String,
//    val definition: String,
//    val genre: String,
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
//    var externalId: String,
//    var result: Response,
//    var name: String,
    val name: String,
    val year: Int,
    val contentProvider: String,
    val attachments: List<Attachment>,
    val externalId: String,
    )
