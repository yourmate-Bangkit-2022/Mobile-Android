package org.firmanmardiyanto.yourmate.di.koin

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.firmanmardiyanto.yourmate.data.repository.AuthRepository
import org.firmanmardiyanto.yourmate.data.repository.ChatRepository
import org.firmanmardiyanto.yourmate.data.repository.ContactRepository
import org.firmanmardiyanto.yourmate.domain.repository.IAuthRepository
import org.firmanmardiyanto.yourmate.domain.repository.IChatRepository
import org.firmanmardiyanto.yourmate.domain.repository.IContactRepository
import org.firmanmardiyanto.yourmate.domain.usecase.*
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel
import org.firmanmardiyanto.yourmate.viewmodels.ChatViewModel
import org.firmanmardiyanto.yourmate.viewmodels.ContactsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

//val firebaseModule = module {
//    single { FirebaseDatabase.getInstance() }
//    single { FirebaseAuth.getInstance() }
//}
//
//val repositoryModule = module {
//    single<IAuthRepository> {
//        AuthRepository(
//            get(), get()
//        )
//    }
//    single<IContactRepository> {
//        ContactRepository(
//            get(), get()
//        )
//    }
//    single<IChatRepository> {
//        ChatRepository(
//            get(), get()
//        )
//    }
//}
//
//val useCaseModule = module {
//    factory<AuthUseCase> { AuthInteractor(get()) }
//    factory<ContactUseCase> { ContactInteractor(get()) }
//    factory<ChatUseCase> { ChatInteractor(get()) }
//}
//
//val viewModelModule = module {
//    viewModel { AuthViewModel(get()) }
//    viewModel { ContactsViewModel(get()) }
//    viewModel { ChatViewModel(get()) }
//}