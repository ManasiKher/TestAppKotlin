package com.example.testAppKotlin

import java.io.Serializable

data class AboutCanadaDetails(
    var title: String,
    var imageHref: String,
    var description: String
) : Serializable