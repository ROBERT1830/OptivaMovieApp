package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.use_case

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.util.Constants

class GetMovieImage {

    operator fun invoke(trackableMovie: TrackableMovie): String{
        //Get image url
        val imageUrl = trackableMovie.attachments.find {
            it.name == Constants.IMAGE_NAME
        }.run {
            this?.let {
                this.value
            }
        }
        return imageUrl?:""
    }
}