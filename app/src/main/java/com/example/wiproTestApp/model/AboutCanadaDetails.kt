package com.example.wiproTestApp

import java.io.Serializable

data class AboutCanadaDetails(
    var title: String,
    var imageHref: String,
    var description: String
) : Serializable