package org.firmanmardiyanto.yourmate.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.firmanmardiyanto.yourmate.domain.usecase.ChatUseCase
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatUseCase: ChatUseCase) : ViewModel() {
    fun getAllChats() = chatUseCase.getAllChats().asLiveData()
    fun getMessagesWith(userId: String) = chatUseCase.getMessagesWith(userId).asLiveData()
    fun sendMessageTo(userId: String, message: String, messagingToken: String) =
        chatUseCase.sendMessageTo(userId, message, messagingToken).asLiveData()
}