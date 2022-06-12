package org.firmanmardiyanto.yourmate.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.Message
import org.firmanmardiyanto.yourmate.domain.model.User
import org.firmanmardiyanto.yourmate.domain.repository.IChatRepository
import java.sql.Date
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) :
    IChatRepository {
    override fun getMessagesWith(userId: String): Flow<Resource<List<Message>>> = flow {
        try {
            emit(Resource.Loading())
            val user = auth.currentUser!!
            val chatRef = database.getReference("chats")
            val chats = chatRef.child(user.uid).child(userId).get().await()
            val messages = mutableListOf<Message>()
            chats.children.forEach {
                val message = it.getValue(Message::class.java)
                if (message != null) {
                    messages.add(message)
                }
            }

            val messagesWithFlag = messages.mapIndexed { index: Int, message: Message ->
                if (index == 0) {
                    message.toMessageWithFirstMessageInDays(true)
                } else {
                    val date = Date(message.timestamp!!)
                    val previousDate = Date(messages[index - 1].timestamp!!)
                    if (date.date != previousDate.date) {
                        message.toMessageWithFirstMessageInDays(true)
                    } else {
                        message.toMessageWithFirstMessageInDays(false)
                    }
                }
            }

            emit(Resource.Success(messagesWithFlag))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }.flowOn(Dispatchers.IO)


    override fun sendMessageTo(userId: String, message: String): Flow<Resource<Message>> = flow {
        try {
            emit(Resource.Loading())
            val user = auth.currentUser!!
            val chatRef = database.getReference("chats")
            val chat = Message(
                text = message,
                timestamp = System.currentTimeMillis(),
                from = "me"
            )
            chatRef.child(user.uid).child(userId).push().setValue(
                chat
            ).await()
            val chatThem = Message(
                text = message,
                timestamp = System.currentTimeMillis(),
                from = "you"
            )
            chatRef.child(userId).child(user.uid).push().setValue(
                chatThem
            ).await()
            emit(
                Resource.Success(
                    chat
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }

    override fun getLastChats(): Flow<Resource<List<Message>>> {
        TODO("Not yet implemented")
    }

    fun getContact(id: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val userRef = database.reference.child("users").child(id)
                .get().await()
            val userData = userRef.getValue(User::class.java)
            if (userData != null) {
                emit(Resource.Success(userData))
            } else {
                emit(Resource.Success(User(id, "", "online")))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}