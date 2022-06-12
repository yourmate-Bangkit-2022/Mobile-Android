package org.firmanmardiyanto.yourmate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.User
import org.firmanmardiyanto.yourmate.domain.repository.IAuthRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val authRepository: IAuthRepository) :
    AuthUseCase {
    override fun signIn(
        email: String,
        password: String
    ): Flow<Resource<User>> = authRepository.signIn(email, password)

    override fun signUp(
        email: String,
        password: String,
        name: String
    ): Flow<Resource<User>> = authRepository.signUp(email, password, name)

    override fun signOut(): Flow<Resource<Boolean>> = authRepository.signOut()

    override fun getCurrentUser(): Flow<Resource<User>> = authRepository.getCurrentUser()

    override fun sendResetPassword(email: String): Flow<Resource<Boolean>> =
        authRepository.sendResetPassword(email)

    override fun updateMessagingToken(token: String): Flow<Resource<Boolean>> =
        authRepository.updateMessagingToken(token)

    override fun getCurrentMessagingToken(): Flow<Resource<String>> =
        authRepository.getCurrentMessagingToken()
}