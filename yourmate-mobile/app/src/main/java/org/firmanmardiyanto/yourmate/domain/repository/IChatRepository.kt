package org.firmanmardiyanto.yourmate.domain.repository

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.Message

interface IChatRepository {
    fun getMessagesWith(userId: String): Flow<Resource<List<Message>>>
    fun sendMessageTo(userId: String, message: String): Flow<Resource<Message>>
    fun getLastChats(): Flow<Resource<List<Message>>>
}