package com.carlosjr.eventsapp.domain.usecase

import com.carlosjr.eventsapp.domain.model.bo.toEventsVO
import com.carlosjr.eventsapp.data.repository.EventsRepository
import com.carlosjr.eventsapp.presentation.model.vo.EventsVO
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
