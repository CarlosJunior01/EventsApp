package com.carlosjr.eventsapp.data.remote

data class CustomNetworkError(override val message: String? = null) : Throwable()