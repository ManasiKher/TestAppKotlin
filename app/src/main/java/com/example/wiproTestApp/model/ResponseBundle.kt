package com.example.wiproTestApp.model

import com.example.wiproTestApp.CanadaDetails
import java.io.Serializable

/*
* response bundle to wrap response details
* */
data class ResponseBundle(
    var canadaDetails: CanadaDetails?,
    var responseStatus: String
) : Serializable