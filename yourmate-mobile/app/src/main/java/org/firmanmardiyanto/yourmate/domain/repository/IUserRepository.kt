package org.firmanmardiyanto.yourmate.domain.repository

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.User

interface IUserRepository {
    fun getUserById(id: Int): Flow<Resource<User>>
}