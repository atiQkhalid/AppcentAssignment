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

    val photoItemList = MutableLiveData<List<Photo>>()
    private val photos = ArrayList<Photo>()
    val cameraList = MutableLiveData<List<String>>()
    private val camHasMap = HashMap<String, String>()

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
                                photos.addAll(it)
                                photoItemList.value = it
                            } ?: getView().onUpdateResponse("Something went wrong")

                            photos.forEach {
                                camHasMap[it.camera.name] = it.camera.full_name
                            }
                            val keyList = ArrayList(camHasMap.keys)
                            val valueList = ArrayList(camHasMap.values)
                            cameraList.value = valueList
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