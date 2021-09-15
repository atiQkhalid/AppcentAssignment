package com.example.appcentassignment.network

import com.example.appcentassignment.models.response.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The APIInterface.kt
 */
interface ApiInterface {

    @GET("everything")
    fun getItems(
        @Query("q") keyword: String, @Query("page") page: String,
        @Query("apiKey") apiKey: String,
    ): Call<ItemResponse>
}