package com.example.wiproTestApp.model

import com.example.wiproTestApp.CanadaDetails
import java.io.Serializable

data class ResponseBundle(
    var canadaDetails: CanadaDetails?,
    var responseStatus: String
) : Serializable