package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.ApiResponse
import retrofit2.http.GET

interface MovieApi {

    @GET("stv/api/rtv/v1/GetUnifiedList?\n" +
            "client=json&statuses=published&definitions=SD;HD;4K&external_category_id=S\n" +
            "ED_3880&filter_empty_categories=true")
    suspend fun searchMovies(): ApiResponse


}