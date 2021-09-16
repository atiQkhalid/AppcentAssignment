package com.example.appcentassignment.views.fragment.spirit

import androidx.lifecycle.MutableLiveData
import com.example.appcentassignment.base.BaseViewModel
import com.example.appcentassignment.models.response.ItemResponse
import com.example.appcentassignment.models.response.Photo
import com.example.appcentassignment.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpiritViewModel : BaseViewModel<SpiritViewModel.View>() {

    val itemList = MutableLiveData<List<Photo>>()

    fun getSpiritItemList() {
        getView().showProgressBar()
        itemRepository.getItemList(keyword = "spirit", apiKey = Constants.API_KEY)
            .enqueue(object : Callback<ItemResponse> {
                override fun onResponse(
                    call: Call<ItemResponse>,
                    response: Response<ItemResponse>
                ) {
                    getView().dismissProgressBar()
                    response.run {
                        if (isSuccessful) {
                            body()?.photos?.let {
                                itemList.value = it
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
}