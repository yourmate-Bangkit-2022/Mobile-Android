package org.firmanmardiyanto.yourmate.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import org.firmanmardiyanto.yourmate.domain.usecase.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {
    val currentUser = authUseCase.getCurrentUser().asLiveData()

    val currentMessagingToken = authUseCase.getCurrentMessagingToken().asLiveData()

    fun login(email: String, password: String) =
        authUseCase.signIn(email, password).asLiveData()

    fun register(email: String, password: String, name: String) =
        authUseCase.signUp(email, password, name).asLiveData()

    fun updateMessagingToken(token: String) = authUseCase.updateMessagingToken(token).asLiveData()

    fun logout() = authUseCase.signOut().asLiveData()
}