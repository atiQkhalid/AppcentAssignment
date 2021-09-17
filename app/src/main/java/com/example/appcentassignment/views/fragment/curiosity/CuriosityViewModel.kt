package com.example.appcentassignment.views.fragment.curiosity

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.appcentassignment.base.BaseViewModel
import com.example.appcentassignment.models.response.ItemResponse
import com.example.appcentassignment.models.response.Photo
import com.example.appcentassignment.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CuriosityViewModel : BaseViewModel<CuriosityViewModel.View>() {

    val photoItemList = MutableLiveData<List<Photo>>()
    private val photos = ArrayList<Photo>()
    val cameraList = MutableLiveData<List<String>>()
    private val camHasMap = HashMap<String, String>()
    private val selectedCamera = MutableLiveData<String>()

    private val filteredData = Transformations.switchMap(selectedCamera) { filterable ->
        Transformations.map(photoItemList) { list ->
            if (filterable.isNullOrEmpty().not()) {
                list.filter {
                    it.camera.full_name.contains(filterable)
                }
            }
            else
                list
        }
    }

    val cameraListData = MediatorLiveData<List<Photo>>().apply {
        addSource(photoItemList) { value -> this.setValue(value) }
        addSource(filteredData) { value -> this.setValue(value) }
    }

    fun onSearchCamera(query: String) {
            selectedCamera.value = query
    }

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
                                photos.addAll(it)
                                photoItemList.value = it
                            } ?: getView().onUpdateResponse("Something went wrong")

                            photos.forEach {
                                camHasMap[it.camera.name] = it.camera.full_name
                            }
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