package com.carlosjr.eventsapp.data.dto

data class CheckInRequest(
    val eventId: String,
    val name: String,
    val email: String
)