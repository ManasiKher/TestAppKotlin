package com.example.wiproTestApp

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import javax.inject.Inject


class CanadaDetailsViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private var mCanadaDetails = MutableLiveData<DetailsData>()

    private val error = MutableLiveData<String>()

    fun getDetails(): MutableLiveData<DetailsData> = mCanadaDetails

    fun getErrors() = error

    fun getData() {
        mCanadaDetails.value?.let {
            return
        }
        mCanadaDetails = dataRepository.getDetails()
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("DISPOSE", "----- disposed -------")
        dataRepository.getCompositeDisposable().clear()
        dataRepository.getCompositeDisposable().dispose()
    }

}