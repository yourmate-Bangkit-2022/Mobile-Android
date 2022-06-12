package org.firmanmardiyanto.yourmate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.Article

interface ArticleUseCase {
    fun getArticles(): Flow<Resource<List<Article>>>
}