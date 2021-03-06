package com.example.wiproTestApp

import com.example.wiproTestApp.model.Constants
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*
* To call service and create retrofit builder instance
* */
val mainModule = module {

    single { DataRepository(get()) }

    single { createWebService() }

    viewModel { CanadaDetailsViewModel(get()) }

}

fun createWebService(): NetWorkApi {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    return retrofit.create(NetWorkApi::class.java)
}