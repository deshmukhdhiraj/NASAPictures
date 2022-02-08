package com.sensibol.android.base.com.nasapictures.gui.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nasapictures.base.BaseViewModel
import com.nasapictures.gui.model.ResponseDataItem
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
internal class LandingViewModel(
    application: Application
) : BaseViewModel(application) {
    private val _getNasaImages: MutableLiveData<List<ResponseDataItem>> by lazy { MutableLiveData<List<ResponseDataItem>>() }
    internal val getNasaImages: LiveData<List<ResponseDataItem>> get() = _getNasaImages

    internal fun getImages() {
        launchUseCases {
            val getNasaImages: List<ResponseDataItem>? = getAssetPodcasts()
            _getNasaImages.postValue(getNasaImages)
        }
    }

    fun getAssetPodcasts(): List<ResponseDataItem>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        val file = "data.json"

        val myjson = context.assets.open(file).bufferedReader().use { it.readText() }

        val listType  = Types.newParameterizedType(
            MutableList::class.java,
           ResponseDataItem::class.java
        )
        val adapter: JsonAdapter<List<ResponseDataItem>> = moshi.adapter(listType)

//        val listType  = Types.newParameterizedType(List::class.java, ResponseData::class.java)
//        val adapter: JsonAdapter<List<ResponseDataItem>> = moshi.adapter(listType)
        return adapter.fromJson(myjson)
    }
}