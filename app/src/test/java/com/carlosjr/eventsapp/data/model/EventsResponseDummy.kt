package com.carlosjr.eventsapp.data.model

import com.carlosjr.eventsapp.data.dto.CheckInRequest
import com.carlosjr.eventsapp.data.dto.EventsDTO
import retrofit2.Response

object EventsResponseDummy {
    fun getListEventsResponseDummy(): Response<List<EventsDTO>> =
        Response.success(listOf(getEventsResponseDummy()))

    private fun getEventsResponseDummy() = EventsDTO(
        date = 0,
        description = "",
        id = "",
        image = "",
        latitude = 0.0,
        longitude = 0.0,
        price = 0.0,
        title = ""
    )

    fun getCheckInRequestDummy() = CheckInRequest(
        eventId = "",
        name = "",
        email = ""
    )
}