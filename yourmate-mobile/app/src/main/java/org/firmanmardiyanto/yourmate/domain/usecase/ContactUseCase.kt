package org.firmanmardiyanto.yourmate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.User

interface ContactUseCase {
    fun getContact(id: String): Flow<Resource<User>>
    fun getContacts(): Flow<Resource<List<User>>>
}