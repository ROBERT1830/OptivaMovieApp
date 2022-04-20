package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote

import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.ApiResponse
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.data.remote.dto.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("GetUnifiedList?client=json&external_category_id=U7D_14039&statuses=published&definitions=SD;HD&order=asc&order_by=tree&from=0&count=5")
    suspend fun searchMovies(): ApiResponse

    @GET("GetVideo?\n" +
            "client=json")
    suspend fun searchMovie(
        @Query("external_id") externalId:String
    ):Response
}