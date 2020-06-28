package com.example.testAppKotlin

import android.app.Application
import com.example.testAppKotlin.di.NetworkComponent

class MainApplication : Application() {

    lateinit var networkComponent: NetworkComponent
    override fun onCreate() {
        super.onCreate()

        networkComponent = DaggerNetworkComponent.builder()
            .networkModule(NetworkModule)
            .build()
    }
}