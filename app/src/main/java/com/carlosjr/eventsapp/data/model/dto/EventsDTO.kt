package com.carlosjr.eventsapp.data.model.dto

import com.carlosjr.eventsapp.domain.model.bo.EventsBO

data class EventsDTO(
    val date: Long,
    val description: String,
    val id: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val price: Double,
    val title: String
)

fun EventsDTO.toEventsBO(): EventsBO {
    return EventsBO(
        date = date,
        description = description,
        id = id,
        image = image,
        latitude = latitude,
        longitude = longitude,
        price = price,
        title = title
    )
}