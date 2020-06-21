package com.example.wiproTestApp

import retrofit2.Call
import retrofit2.Response

/*
* Handles service calls and request response
* */
class DataRepository(val netWorkApi: NetWorkApi) {

    fun getDetails(onDetailsData: OnDetailsData) {
        netWorkApi.getCanadaDetails().enqueue(object : retrofit2.Callback<CanadaDetails> {
            override fun onResponse(call: Call<CanadaDetails>, response: Response<CanadaDetails>) {
                onDetailsData.onSuccess((response.body() as CanadaDetails))
            }

            override fun onFailure(call: Call<CanadaDetails>, t: Throwable) {
                onDetailsData.onFailure()
            }
        })
    }

    interface OnDetailsData {
        fun onSuccess(data: CanadaDetails)
        fun onFailure()
    }
}

