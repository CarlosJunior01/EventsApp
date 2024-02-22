package com.carlosjr.eventsapp.data.repository

import com.carlosjr.eventsapp.data.dto.toEventsBO
import com.carlosjr.eventsapp.data.remote.EventsAPI
import com.carlosjr.eventsapp.domain.bo.EventsBO
import com.carlosjr.eventsapp.domain.repository.EventsRepository
import javax.inject.Inject

class EventsRepositoryImp @Inject constructor(
    private val api: EventsAPI
): EventsRepository {
    override suspend fun getEvents(): List<EventsBO> {
        val listResponse = api.getEvents()
        if (listResponse.isSuccessful){
            val eventsDTO = listResponse.body()
            if (eventsDTO != null){
                return eventsDTO.map {
                    it.toEventsBO()
                }
            }
        }
        return emptyList()
    }
}