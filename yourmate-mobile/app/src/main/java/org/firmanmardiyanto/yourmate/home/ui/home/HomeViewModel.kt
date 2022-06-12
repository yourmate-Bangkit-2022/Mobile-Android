package org.firmanmardiyanto.yourmate.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.firmanmardiyanto.yourmate.domain.usecase.ArticleUseCase
import org.firmanmardiyanto.yourmate.domain.usecase.PlaceUseCase
import org.firmanmardiyanto.yourmate.domain.usecase.UserUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    placeUseCase: PlaceUseCase,
    articleUseCase: ArticleUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {
    val articles = articleUseCase.getArticles().asLiveData()
    val places = placeUseCase.getPlaces().asLiveData()
    fun getUserById(id: Int) = userUseCase.getUserById(id).asLiveData()
}