package org.firmanmardiyanto.yourmate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.Message
import org.firmanmardiyanto.yourmate.domain.model.User

interface ChatUseCase {
    fun getMessagesWith(userId: String): Flow<Resource<List<Message>>>
    fun sendMessageTo(
        userId: String,
        message: String,
        messagingToken: String
    ): Flow<Resource<Message>>

    fun getLastChats(): Flow<Resource<List<Message>>>
    fun getAllChats(): Flow<Resource<List<User>>>
}