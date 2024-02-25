package com.carlosjr.eventsapp.domain.usecase

import com.carlosjr.eventsapp.data.model.EventsResponseDummy.getCheckInRequestDummy
import com.carlosjr.eventsapp.data.repository.EventsRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CheckInUseCaseTest {

    private lateinit var checkInUseCase: CheckInUseCase

    @MockK
    lateinit var repository: EventsRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        checkInUseCase = CheckInUseCase(repository)
    }

    @Test
    fun `when checkIn return success with a events list`() = runBlocking {

        //GIVEN
        coEvery {
            repository.checkInEvent(getCheckInRequestDummy())
        } returns flow { }

        //WHEN
        val result = checkInUseCase.invoke(getCheckInRequestDummy())

        //THEN
        assertNotNull(result)
    }

    @Test(expected = Exception::class)
    fun `getAllCards throws generic exception`(): Unit = runBlocking {

        //GIVEN
        coEvery { repository.getEvents() } throws Exception()
            .apply {
                repository.checkInEvent(getCheckInRequestDummy())
            }
    }
}