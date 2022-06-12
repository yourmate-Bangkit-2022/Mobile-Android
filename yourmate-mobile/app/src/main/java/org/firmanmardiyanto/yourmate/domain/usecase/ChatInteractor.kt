package org.firmanmardiyanto.yourmate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.Message
import org.firmanmardiyanto.yourmate.domain.model.User
import org.firmanmardiyanto.yourmate.domain.repository.IChatRepository
import javax.inject.Inject

class ChatInteractor @Inject constructor(private val chatRepository: IChatRepository) :
    ChatUseCase {
    override fun getMessagesWith(userId: String): Flow<Resource<List<Message>>> =
        chatRepository.getMessagesWith(userId)

    override fun sendMessageTo(
        userId: String,
        message: String,
        messagingToken: String
    ): Flow<Resource<Message>> =
        chatRepository.sendMessageTo(userId, message, messagingToken)

    override fun getLastChats(): Flow<Resource<List<Message>>> = chatRepository.getLastChats()

    override fun getAllChats(): Flow<Resource<List<User>>> = chatRepository.getAllChats()
}