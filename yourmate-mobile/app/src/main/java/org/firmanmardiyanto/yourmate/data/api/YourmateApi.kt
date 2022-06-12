package org.firmanmardiyanto.yourmate.data.api

import org.firmanmardiyanto.yourmate.domain.model.Article
import org.firmanmardiyanto.yourmate.domain.model.Place
import retrofit2.Response
import retrofit2.http.GET

interface YourmateApi {
    @GET("articles")
    suspend fun getArticles(): Response<BaseListResponse<Article>>

    @GET("places")
    suspend fun getPlaces() : Response<BaseListResponse<Place>>
}