package com.carlosjr.eventsapp.domain.usecase

import com.carlosjr.eventsapp.presentation.model.vo.EventsVO

interface GetEventsUseCase {
    suspend fun getEvents(): List<EventsVO>
}