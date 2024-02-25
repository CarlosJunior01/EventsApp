package com.carlosjr.eventsapp.data.repository

import com.carlosjr.eventsapp.data.dto.CheckInRequest
import com.carlosjr.eventsapp.data.dto.toEventsBO
import com.carlosjr.eventsapp.data.remote.CustomNetworkError
import com.carlosjr.eventsapp.data.remote.EventsAPI
import com.carlosjr.eventsapp.domain.model.bo.EventsBO
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class EventsRepositoryImp @Inject constructor(
    private val api: EventsAPI
): EventsRepository {
    override suspend fun getEvents(): List<EventsBO> {
        val listResponse = api.getEvents()
        if (listResponse.isSuccessful){
            val eventsDTO = listResponse.body()
            if (eventsDTO != null){
                return eventsDTO.map {
                    it.toEventsBO()
                }
            }
        }
        return emptyList()
    }

    override fun checkInEvent(checkIn: CheckInRequest): Flow<Void> = flow  {
        emit(handleApiCall { api.postCheckIn(checkIn) })
    }.flowOn(Dispatchers.IO)

    private inline fun <reified T> handleApiCall(apiCall: () -> Response<T>): T {
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

    data class ErrorResponse(
        @SerializedName("message")
        val message: String? = null
    )
}