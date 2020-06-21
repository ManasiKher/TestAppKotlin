package com.example.wiproTestApp

import java.io.Serializable


data class CanadaDetails(
    var rows: List<AboutCanadaDetails>,
    var title: String
) : Serializable