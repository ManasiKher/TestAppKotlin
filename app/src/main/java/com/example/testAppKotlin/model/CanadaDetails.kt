package com.example.testAppKotlin

import java.io.Serializable

sealed class DetailsData
data class CanadaDetails(
    var rows: List<AboutCanadaDetails>,
    var title: String) : Serializable, DetailsData() {
}

data class ErrorDetails(val msg: String) : DetailsData()

