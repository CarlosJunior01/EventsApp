package com.carlosjr.eventsapp.domain.bo

import com.carlosjr.eventsapp.presentation.model.EventsVO

data class EventsBO(
    val date: Long,
    val description: String,
    val id: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val price: Double,
    val title: String
)

fun EventsBO.toEventsVO(): EventsVO {
    return EventsVO(
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