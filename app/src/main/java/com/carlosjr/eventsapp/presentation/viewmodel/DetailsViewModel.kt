package com.carlosjr.eventsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosjr.eventsapp.R
import com.carlosjr.eventsapp.data.model.dto.CheckInRequest
import com.carlosjr.eventsapp.domain.usecase.CheckInUseCase
import com.carlosjr.eventsapp.helper.extensions.handleTimeoutApiError
import com.carlosjr.eventsapp.presentation.model.UiText.Companion.resourceToUiText
import com.carlosjr.eventsapp.presentation.model.exception.ViewException
import com.carlosjr.eventsapp.presentation.model.viewstate.DetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: CheckInUseCase
): ViewModel() {

    private val _eventsStateFlow = MutableStateFlow<DetailsViewState>(DetailsViewState.LoadingState)
    val eventsStateFlow get() = _eventsStateFlow.asStateFlow()

    fun sendCheckIn(checkIn: CheckInRequest) {
        viewModelScope.launch {
            useCase.invoke(checkIn)
                .onStart {
                    _eventsStateFlow.value = DetailsViewState.LoadingState
                }.catch { error ->
                    handleTimeoutApiError(
                        error, R.string.message_error_timeout.resourceToUiText()
                    ).let { errorMessage ->
                        _eventsStateFlow.emit(DetailsViewState.ErrorState(ViewException(errorMessage)))
                    }
                }.collect {
                    _eventsStateFlow.value = DetailsViewState.SuccessState
                }
        }
    }
}