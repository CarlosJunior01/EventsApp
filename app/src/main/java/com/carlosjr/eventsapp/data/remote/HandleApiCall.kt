package com.carlosjr.eventsapp.data.remote

import com.carlosjr.eventsapp.data.model.error.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

inline fun <reified T> handleApiCall(apiCall: () -> Response<T>): T {
    val response = apiCall()

    if (response.isSuccessful) {
        val body = response.body()
        return if (body is T) body
        else Gson().fromJson(response.errorBody()?.string(), T::class.java)

    } else {
        response.errorBody()?.string()?.let {
            val errorMessage = Gson().fromJson(it, ErrorResponse::class.java)
            throw CustomNetworkError(errorMessage.message)
        } ?: throw Throwable("ERROR_ON_PARSE_JSON")
    }
}