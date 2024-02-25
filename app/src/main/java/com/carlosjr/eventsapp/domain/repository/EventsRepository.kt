package com.carlosjr.eventsapp.domain.repository

import com.carlosjr.eventsapp.data.dto.CheckInRequest
import com.carlosjr.eventsapp.domain.model.bo.EventsBO
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    suspend fun getEvents(): List<EventsBO>
    fun checkInEvent(checkIn: CheckInRequest): Flow<Void>
}