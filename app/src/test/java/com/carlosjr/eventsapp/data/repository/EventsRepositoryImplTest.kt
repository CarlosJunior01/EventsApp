package com.carlosjr.eventsapp.data.repository

import com.carlosjr.eventsapp.data.model.EventsResponseDummy
import com.carlosjr.eventsapp.data.model.EventsResponseDummy.getCheckInRequestDummy
import com.carlosjr.eventsapp.data.remote.EventsAPI
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class EventsRepositoryImplTest {

    private lateinit var eventsRepository: EventsRepositoryImpl

    @MockK
    lateinit var eventsAPI: EventsAPI

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        eventsRepository = EventsRepositoryImpl(eventsAPI)
    }

    @Test
    fun `getEvents return success with a list of event`() = runBlocking {

        //GIVEN
        coEvery { eventsAPI.getEvents() } returns
                EventsResponseDummy.getListEventsResponseDummy()

        //WHEN
        val result = eventsRepository.getEvents()

        //THEN
        assertTrue(result.isNotEmpty())
    }

    @Test(expected = Exception::class)
    fun `getEvents throws generic exception`(): Unit = runBlocking {

        //GIVEN
        coEvery {
            eventsAPI.getEvents()
        } throws Exception()
            .apply {
                eventsRepository.getEvents()
            }
    }

    @Test
    fun `check in return success`() = runBlocking {

        //GIVEN
        coEvery { eventsAPI.postCheckIn(getCheckInRequestDummy()) }

        //WHEN
        val result = eventsRepository.checkInEvent(getCheckInRequestDummy())

        Assert.assertNotNull(result)

    }

    @Test(expected = Exception::class)
    fun `check in throws generic exception`(): Unit = runBlocking {

        //GIVEN
        coEvery {
            eventsAPI.postCheckIn(getCheckInRequestDummy())
        } throws Exception()
            .apply {
            eventsRepository.getEvents()
        }
    }
}
