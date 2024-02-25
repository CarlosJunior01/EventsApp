package com.carlosjr.eventsapp.domain.usecase

import com.carlosjr.eventsapp.domain.bo.toEventsVO
import com.carlosjr.eventsapp.domain.repository.EventsRepository
import com.carlosjr.eventsapp.presentation.model.EventsVO
import javax.inject.Inject

class GetEventsUseCaseImpl@Inject constructor(
    private val eventsRepository: EventsRepository
): GetEventsUseCase {

    override suspend fun getEvents(): List<EventsVO> {
        try {
            val eventsList = eventsRepository.getEvents()
            return eventsList.map { eventsBO ->
                eventsBO.toEventsVO()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}
