package com.example.appcentassignment.views.fragment.curiosity

import androidx.lifecycle.MutableLiveData
import com.example.appcentassignment.base.BaseViewModel
import com.example.appcentassignment.models.response.Camera
import com.example.appcentassignment.models.response.ItemResponse
import com.example.appcentassignment.models.response.Photo
import com.example.appcentassignment.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CuriosityViewModel : BaseViewModel<CuriosityViewModel.View>() {

    val itemList = MutableLiveData<List<Photo>>()
    val photo = ArrayList<Photo>()

    fun getCuriosityItemList() {
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
                            body()?.photos?.let {
                                itemList.value = it
                                photo.addAll(it)
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