package org.firmanmardiyanto.yourmate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.domain.model.User
import org.firmanmardiyanto.yourmate.domain.repository.IContactRepository

class ContactInteractor(private val contactRepository: IContactRepository) : ContactUseCase {
    override fun getContact(id: String): Flow<Resource<User>> = contactRepository.getContact(id)

    override fun getContacts(): Flow<Resource<List<User>>> = contactRepository.getContacts()
}