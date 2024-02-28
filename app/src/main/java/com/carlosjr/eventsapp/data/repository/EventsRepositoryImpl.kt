package com.carlosjr.eventsapp.data.repository

import com.carlosjr.eventsapp.data.model.dto.CheckInRequest
import com.carlosjr.eventsapp.data.model.dto.toEventsBO
import com.carlosjr.eventsapp.data.remote.EventsAPI
import com.carlosjr.eventsapp.data.remote.handleApiCall
import com.carlosjr.eventsapp.domain.model.bo.EventsBO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
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

    override fun checkInEvent(checkIn: CheckInRequest): Flow<Void> = flow  {
        emit(handleApiCall { api.postCheckIn(checkIn) })
    }.flowOn(Dispatchers.IO)
}