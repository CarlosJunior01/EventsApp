package com.carlosjr.eventsapp.domain.usecase

import com.carlosjr.eventsapp.data.repository.EventsRepositoryImpl
import com.carlosjr.eventsapp.domain.model.EventsDomainDummy
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GetEventsUseCaseImplTest {
    private lateinit var getEventsUseCase: GetEventsUseCaseImpl

    @MockK
    lateinit var repository: EventsRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getEventsUseCase = GetEventsUseCaseImpl(repository)
    }

    @Test
    fun `when getEvents return success with a events list`() = runBlocking {

        //GIVEN
        coEvery {
            repository.getEvents()
        } returns EventsDomainDummy.getListEventsResponseDummy()

        //WHEN
        val result = getEventsUseCase.getEvents()

        //THEN
        assertNotNull(result)
    }

    @Test(expected = Exception::class)
    fun `getEvents throws generic exception`(): Unit = runBlocking {

        //GIVEN
        coEvery { repository.getEvents() } throws Exception()
            .apply {
                repository.getEvents()
            }
    }
}