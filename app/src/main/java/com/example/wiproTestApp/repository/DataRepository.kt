package com.example.wiproTestApp

import retrofit2.Call
import retrofit2.Response

class DataRepository(val netWorkApi: NetWorkApi) {

    fun getProducts(onProductData: OnProductData) {
        netWorkApi.getProducts().enqueue(object : retrofit2.Callback<CanadaDetails> {
            override fun onResponse(call: Call<CanadaDetails>, response: Response<CanadaDetails>) {
                onProductData.onSuccess((response.body() as CanadaDetails))
            }

            override fun onFailure(call: Call<CanadaDetails>, t: Throwable) {
                onProductData.onFailure()
            }
        })
    }

    interface OnProductData {
        fun onSuccess(data: CanadaDetails)
        fun onFailure()
    }
}

