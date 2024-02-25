package com.carlosjr.eventsapp.presentation.model

import com.carlosjr.eventsapp.presentation.model.vo.EventsVO

object EventsViewDummy {
    fun getListEventsViewDummy(): List<EventsVO> = listOf(getEventsViewDummy())

    private fun getEventsViewDummy() = EventsVO(
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