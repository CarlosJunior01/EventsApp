package com.carlosjr.eventsapp.domain.repository

import com.carlosjr.eventsapp.domain.bo.EventsBO

interface EventsRepository {
    suspend fun getEvents(): List<EventsBO>
}