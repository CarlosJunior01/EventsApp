package com.carlosjr.eventsapp.data.remote

import com.carlosjr.eventsapp.data.model.dto.CheckInRequest
import com.carlosjr.eventsapp.data.model.dto.EventsDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventsAPI {

    @GET("api/events")
    suspend fun getEvents(): Response<List<EventsDTO>>

    @POST("api/checkin")
    suspend fun postCheckIn(@Body checkIn: CheckInRequest) : Response<Void>
}