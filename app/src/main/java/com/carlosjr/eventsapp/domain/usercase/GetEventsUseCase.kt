package com.carlosjr.eventsapp.domain.usercase

import com.carlosjr.eventsapp.presentation.model.EventsVO

interface GetEventsUseCase {
    suspend fun getEvents(): List<EventsVO>
}