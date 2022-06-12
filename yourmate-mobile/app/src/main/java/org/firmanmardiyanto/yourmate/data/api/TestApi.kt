package org.firmanmardiyanto.yourmate.data.api

import retrofit2.Response
import retrofit2.http.GET

interface TestApi {
    @GET("test")
    suspend fun test(): Response<Unit>
}