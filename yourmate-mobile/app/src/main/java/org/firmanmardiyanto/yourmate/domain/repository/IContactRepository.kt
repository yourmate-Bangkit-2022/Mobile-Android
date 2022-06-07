package org.firmanmardiyanto.yourmate.domain.repository

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.User

interface IContactRepository {
    fun getContacts(): Flow<Resource<List<User>>>
    fun getContact(id: String): Flow<Resource<User>>
}