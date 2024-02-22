package com.carlosjr.eventsapp.data.remote

import com.carlosjr.eventsapp.data.dto.EventsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsAPI {

    @GET("api/events")
    suspend fun getEvents(): Response<List<EventsDTO>>

    @GET("api/events{id}")
    suspend fun getEventsById(
        @Path("idPost") id: Int
    ): Response<EventsDTO>
}