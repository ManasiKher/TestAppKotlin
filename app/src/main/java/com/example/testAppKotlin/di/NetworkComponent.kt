package com.example.testAppKotlin.di

import com.example.testAppKotlin.MainActivity
import com.example.testAppKotlin.NetworkModule
import com.example.testAppKotlin.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface NetworkComponent {
    fun inject(activity: MainActivity)
}