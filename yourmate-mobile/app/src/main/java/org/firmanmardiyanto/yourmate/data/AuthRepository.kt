package org.firmanmardiyanto.yourmate.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import org.firmanmardiyanto.yourmate.domain.model.User
import org.firmanmardiyanto.yourmate.domain.repository.IAuthRepository
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) :
    IAuthRepository {
    override fun signIn(
        email: String,
        password: String
    ): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            auth.signInWithEmailAndPassword(email, password).await()
            val user = auth.currentUser
            val userRef = database.reference.child("users").child(user!!.uid)
                .get().await()
            val userData = userRef.getValue(User::class.java)
            if (userData != null) {
                emit(Resource.Success(userData))
            } else {
                emit(Resource.Success(User(user.uid, "", "online")))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }


    override fun signUp(
        email: String,
        password: String,
        name: String
    ): Flow<Resource<User>> =
        flow {
            try {
                emit(Resource.Loading())
                auth.createUserWithEmailAndPassword(email, password).await()
                val user = User(auth.currentUser?.uid!!, name, "online")
                database.reference.child("users").child(auth.currentUser!!.uid).setValue(
                    user
                ).await()
                emit(Resource.Success(user))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Error"))
            }
        }.flowOn(Dispatchers.IO)


    override fun signOut(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            auth.signOut()
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getCurrentUser(): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val user = auth.currentUser
            val userRef = database.reference.child("users").child(user!!.uid)
                .get().await()
            val userData = userRef.getValue(User::class.java)
            if (userData != null) {
                emit(Resource.Success(userData))
            } else {
                emit(Resource.Success(User(user.uid, "", "online")))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun sendResetPassword(email: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }
}