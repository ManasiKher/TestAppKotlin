package com.example.wiproTestApp


import retrofit2.Call
import retrofit2.http.GET

interface NetWorkApi{

    @GET("facts.json")
    fun getCanadaDetails(): Call<CanadaDetails>

}