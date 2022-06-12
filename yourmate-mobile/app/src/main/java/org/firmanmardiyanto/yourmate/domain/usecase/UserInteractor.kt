package org.firmanmardiyanto.yourmate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.data.repository.UserRepository
import org.firmanmardiyanto.yourmate.domain.model.User
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: UserRepository) :
    UserUseCase {
    override fun getUserById(id: Int): Flow<Resource<User>> = userRepository.getUserById(id)
}
