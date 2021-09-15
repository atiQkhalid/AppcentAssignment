package com.example.appcentassignment.views.fragment.Curiosity

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.appcentassignment.base.BaseViewModel
import com.example.appcentassignment.models.response.ItemResponse
import com.example.appcentassignment.models.response.Photo
import com.example.appcentassignment.utils.Constants.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.coroutines.CoroutineContext

class CuriosityViewModel : BaseViewModel<CuriosityViewModel.View>(), CoroutineScope {

    private val itemList = MutableLiveData<List<Photo>>()

    val itemListData = MediatorLiveData<List<Photo>>().apply {
        addSource(itemList) { value -> this.setValue(value) }
    }

    fun getICuriosityItemLists() {
        getView().showProgressBar()
        itemRepository.getItemList(keyword = "curiosity", apiKey = API_KEY)
            .enqueue(object : Callback<ItemResponse> {
                override fun onResponse(
                    call: Call<ItemResponse>,
                    response: Response<ItemResponse>
                ) {
                    getView().dismissProgressBar()
                    response.run {
                        if (isSuccessful) {
                            body()?.run {
                                this.photos.let {
                                    itemList.value = it
                                }
                            } ?: getView().onUpdateResponse("Something went wrong")
                        }
                    }
                }

                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                    getView().dismissProgressBar()
                    getView().onUpdateResponse(t.message.toString())
                }
            })
    }

    interface View {
        fun onUpdateResponse(message: String)
        fun showProgressBar()
        fun dismissProgressBar()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}