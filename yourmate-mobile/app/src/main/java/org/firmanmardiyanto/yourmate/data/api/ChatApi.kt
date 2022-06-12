package org.firmanmardiyanto.yourmate.data.api

import org.firmanmardiyanto.yourmate.data.model.request.MessageRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {
    @POST("fcm/send")
    suspend fun sendMessage(@Body messageRequest: MessageRequest): Response<Unit>
}