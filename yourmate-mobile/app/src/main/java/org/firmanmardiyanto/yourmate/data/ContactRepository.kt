package org.firmanmardiyanto.yourmate.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import org.firmanmardiyanto.yourmate.domain.model.User
import org.firmanmardiyanto.yourmate.domain.repository.IContactRepository

class ContactRepository(private val auth: FirebaseAuth, private val database: FirebaseDatabase) :
    IContactRepository {
    override fun getContacts(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())
        try {
            val user = auth.currentUser
            val userRef = database.reference.child("users")
                .get().await()
            val contacts = mutableListOf<User>()
            userRef.children.forEach {
                val userData = it.getValue(User::class.java)
                if (userData != null && userData.uid != user?.uid) {
                    contacts.add(userData)
                }
            }
            emit(Resource.Success(contacts as List<User>))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getContact(id: String): Flow<Resource<User>> = flow {
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
