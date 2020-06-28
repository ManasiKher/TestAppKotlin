package com.example.wiproTestApp

import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/*
* Handles service calls and request response
* */
class DataRepository(val netWorkApi: NetWorkApi) {

    fun getDetails(onDetailsData: OnDetailsData) {
        netWorkApi.getCanadaDetails().enqueue(object : retrofit2.Callback<CanadaDetails> {
            override fun onResponse(call: Call<CanadaDetails>, response: Response<CanadaDetails>) {
                data.value = response.body() as CanadaDetails
            }

            override fun onFailure(call: Call<CanadaDetails>, t: Throwable) {
                data.value = ErrorDetails(t.localizedMessage)
            }
        })
        return data
    }

    sealed class OnDetailsData {
        abstract fun onSuccess(data: CanadaDetails)
        abstract fun onFailure()
    }
}

