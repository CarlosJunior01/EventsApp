package com.carlosjr.eventsapp.domain.model

import com.carlosjr.eventsapp.domain.model.bo.EventsBO

object EventsDomainDummy {
    fun getListEventsResponseDummy(): List<EventsBO> = listOf(getEventsResponseDummy())

    private fun getEventsResponseDummy() = EventsBO(
        date = 0,
        description = "",
        id = "",
        image = "",
        latitude = 0.0,
        longitude = 0.0,
        price = 0.0,
        title = ""
    )
}