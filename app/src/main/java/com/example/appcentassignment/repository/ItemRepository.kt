package com.example.appcentassignment.repository

import com.example.appcentassignment.base.BaseRepository
import com.example.appcentassignment.network.RetrofitClient
import com.example.appcentassignment.utils.Constants.BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class ItemRepository : BaseRepository(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    ////API End pints
    fun getItemList(keyword: String, apiKey: String) =
        RetrofitClient.getInterfaceService(
            BASE_URL
        ).getItems(keyword, apiKey)

    companion object {
        private var instance: ItemRepository? = null
        fun getInstance(): ItemRepository {
            if (instance == null)
                instance =
                    ItemRepository()
            return instance!!
        }
    }
}