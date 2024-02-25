package com.carlosjr.eventsapp.helper.extensions

import android.util.Log
import com.carlosjr.eventsapp.R
import com.carlosjr.eventsapp.data.remote.CustomNetworkError
import com.carlosjr.eventsapp.presentation.model.UiText
import com.carlosjr.eventsapp.presentation.model.UiText.Companion.resourceToUiText
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

inline fun <reified T : Throwable> handleTimeoutApiError(
    error: T,
    timeoutMessage: UiText
): UiText {
    Log.d("EventsError", "handleTimeoutApiError", error)
    return when (error) {
        is HttpException -> {
            when (error.code()) {
                HttpURLConnection.HTTP_GATEWAY_TIMEOUT,
                HttpURLConnection.HTTP_CLIENT_TIMEOUT -> timeoutMessage
                else -> UiText.DynamicString(error.message())
            }
        }

        is CustomNetworkError -> {
            error.message?.let {
                UiText.DynamicString(it)
            } ?: R.string.text_error_processing.resourceToUiText()
        }

        is SocketTimeoutException -> timeoutMessage
        else -> R.string.text_error_retry.resourceToUiText()
    }
}