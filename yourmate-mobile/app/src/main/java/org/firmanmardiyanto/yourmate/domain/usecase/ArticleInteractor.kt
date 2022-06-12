package org.firmanmardiyanto.yourmate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.data.repository.ArticleRepository
import org.firmanmardiyanto.yourmate.domain.model.Article
import javax.inject.Inject

class ArticleInteractor @Inject constructor(private val articleRepository: ArticleRepository) :
    ArticleUseCase {
    override fun getArticles(): Flow<Resource<List<Article>>> = articleRepository.getArticles()
}
