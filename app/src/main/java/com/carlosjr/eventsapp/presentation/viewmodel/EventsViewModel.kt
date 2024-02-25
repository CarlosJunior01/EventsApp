package com.carlosjr.eventsapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosjr.eventsapp.domain.usecase.GetEventsUseCase
import com.carlosjr.eventsapp.presentation.model.vo.EventsVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val useCase: GetEventsUseCase
): ViewModel() {

    private val _eventsLiveData = MutableLiveData<List<EventsVO>>()
    val eventsLiveData: MutableLiveData<List<EventsVO>> = _eventsLiveData

    init {
        getEvents()
    }

    private fun getEvents() {
        viewModelScope.launch {
            _eventsLiveData.postValue(useCase.getEvents())
        }
    }
}