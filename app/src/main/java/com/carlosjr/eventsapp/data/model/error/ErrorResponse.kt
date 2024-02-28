package com.carlosjr.eventsapp.data.model.error

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val message: String? = null
)