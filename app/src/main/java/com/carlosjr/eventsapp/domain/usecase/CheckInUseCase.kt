package com.carlosjr.eventsapp.domain.usecase

import com.carlosjr.eventsapp.data.dto.CheckInRequest
import com.carlosjr.eventsapp.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckInUseCase @Inject constructor(private val eventsRepository: EventsRepository) {
    operator fun invoke(checkIn: CheckInRequest): Flow<Void> {
        return eventsRepository.checkInEvent(checkIn)
    }
}
