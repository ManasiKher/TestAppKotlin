package com.example.wiproTestApp

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.koin.standalone.KoinComponent


class CanadaDetailsViewModel(val dataRepository: DataRepository) : ViewModel(), KoinComponent {

    var candaDetailsResposne = MutableLiveData<CanadaDetails>()

    init {
       // candaDetailsResposne.value = listOf()
    }

    fun getDetails() {
        dataRepository.getDetails(object : DataRepository.OnDetailsData {
            override fun onSuccess(data: CanadaDetails) {
                candaDetailsResposne.value = data
            }

            override fun onFailure() {
                //REQUEST FAILED
            }
        })
    }
}