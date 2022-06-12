package org.firmanmardiyanto.yourmate.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.data.api.YourmateApi
import org.firmanmardiyanto.yourmate.domain.model.Article
import org.firmanmardiyanto.yourmate.domain.repository.IArticleRepository
import javax.inject.Inject

private const val TAG = "ArticleRepository"

class ArticleRepository @Inject constructor(
    private val yourmateApi: YourmateApi
) : IArticleRepository {
    override fun getArticles(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            val response = yourmateApi.getArticles()
            if (response.isSuccessful) {
                val articles = response.body()?.data?.map {
                    Article(
                        id = it.id.toInt(),
                        title = it.attributes.title,
                        body = it.attributes.body,
                        publishedAt = it.attributes.publishedAt,
                        imageUrl = it.attributes.imageUrl,
                    )
                }
                if (articles != null) {
                    emit(Resource.Success(articles))
                } else {
                    emit(Resource.Error("No data"))
                }
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }

}