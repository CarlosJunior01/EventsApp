package com.carlosjr.eventsapp.presentation.model

import java.io.Serializable

data class EventsVO(
    val date: Long,
    val description: String,
    val id: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val price: Double,
    val title: String
) : Serializable