package com.carlosjr.eventsapp.presentation.model.viewstate

sealed class DetailsViewState {
    data object LoadingState: DetailsViewState()
    data object SuccessState: DetailsViewState()
    data class ErrorState(val throwable: Throwable): DetailsViewState()
}
