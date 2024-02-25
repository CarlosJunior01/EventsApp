package com.carlosjr.eventsapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.carlosjr.eventsapp.domain.usecase.GetEventsUseCase
import com.carlosjr.eventsapp.presentation.model.EventsViewDummy
import com.carlosjr.eventsapp.utils.MainDispatcherRule
import com.carlosjr.eventsapp.utils.getOrAwaitValue
import com.google.common.truth.Truth
import io.mockk.coEvery
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventsViewModelTest {

    private lateinit var viewModel: EventsViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var eventsUseCase: GetEventsUseCase

    @Before
    fun setUp() = runTest {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `when get events return success`() = runTest  {

        //GIVEN
        Mockito.`when`(eventsUseCase.getEvents()).thenReturn(
            EventsViewDummy.getListEventsViewDummy()
        )

        //WHEN
        viewModel = EventsViewModel(eventsUseCase)
        val result = viewModel.eventsLiveData.getOrAwaitValue()

        //THEN
        Truth.assertThat(result).isNotNull()
    }

    @Test
    fun `when get events return empty list`() = runTest  {

        //GIVEN
        Mockito.`when`(eventsUseCase.getEvents()).thenReturn(
            emptyList()
        )

        //WHEN
        viewModel = EventsViewModel(eventsUseCase)
        val result = viewModel.eventsLiveData.getOrAwaitValue()

        //THEN
        Truth.assertThat(result).isEmpty()
    }

    @Test(expected = Exception::class)
    fun `when get events throws generic exception`(): Unit = runBlocking {

        coEvery { eventsUseCase.getEvents() } throws Exception()
            .apply {
                viewModel.eventsLiveData.getOrAwaitValue()
            }
    }
}