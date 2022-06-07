package org.firmanmardiyanto.yourmate.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import org.firmanmardiyanto.yourmate.domain.usecase.ChatUseCase

class ChatViewModel(private val chatUseCase: ChatUseCase): ViewModel() {
    fun getMessagesWith(userId: String) = chatUseCase.getMessagesWith(userId).asLiveData()
    fun sendMessageTo(userId: String, message: String) = chatUseCase.sendMessageTo(userId, message).asLiveData()
}