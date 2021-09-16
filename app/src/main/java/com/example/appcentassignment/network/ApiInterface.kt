package com.example.appcentassignment.network

import com.example.appcentassignment.models.response.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The APIInterface.kt
 */
interface ApiInterface {

    @GET("{imageKeyword}/photos")
    fun getItems(
        @Path("imageKeyword") imageKeyword: String,
        @Query("sol") itemCount: Int = 100,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): Call<ItemResponse>
}