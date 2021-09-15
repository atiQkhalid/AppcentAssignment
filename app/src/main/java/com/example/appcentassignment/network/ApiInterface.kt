package com.example.appcentassignment.network

import com.example.appcentassignment.models.response.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The APIInterface.kt
 */
interface ApiInterface {

    @GET("curiosity/photos")
    fun getItems(
        @Query("sol") keyword: String,
        @Query("apiKey") apiKey: String,
    ): Call<ItemResponse>
}