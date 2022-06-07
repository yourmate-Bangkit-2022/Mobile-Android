package org.firmanmardiyanto.yourmate.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import org.firmanmardiyanto.yourmate.domain.usecase.ContactUseCase

class ContactsViewModel(private val contactUseCase: ContactUseCase) : ViewModel() {
    val contacts = contactUseCase.getContacts().asLiveData()
}