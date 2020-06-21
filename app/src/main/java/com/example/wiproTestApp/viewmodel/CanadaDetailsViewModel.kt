package com.example.wiproTestApp

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.wiproTestApp.model.Constants
import com.example.wiproTestApp.model.ResponseBundle
import org.koin.standalone.KoinComponent


class CanadaDetailsViewModel(val dataRepository: DataRepository) : ViewModel(), KoinComponent {

    var mCanadaDetailsResposne = MutableLiveData<ResponseBundle>()
    lateinit var mCanadaDetails: CanadaDetails
    lateinit var mResponseBundle: ResponseBundle

    fun getDetails() {
        dataRepository.getDetails(object : DataRepository.OnDetailsData {
            override fun onSuccess(data: CanadaDetails) {
                mCanadaDetails = data
                mResponseBundle = ResponseBundle(data, Constants.SUCCESS)
                mCanadaDetailsResposne.value = mResponseBundle
            }

            override fun onFailure() {
                mResponseBundle = ResponseBundle(null,Constants.FAILED)
                mCanadaDetailsResposne.value = mResponseBundle
            }
        })
    }
}