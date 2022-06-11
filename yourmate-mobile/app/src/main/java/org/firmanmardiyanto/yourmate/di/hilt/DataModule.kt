package org.firmanmardiyanto.yourmate.di.hilt

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.firmanmardiyanto.yourmate.data.AuthRepository
import org.firmanmardiyanto.yourmate.data.ChatRepository
import org.firmanmardiyanto.yourmate.data.ContactRepository
import org.firmanmardiyanto.yourmate.domain.repository.IAuthRepository
import org.firmanmardiyanto.yourmate.domain.repository.IChatRepository
import org.firmanmardiyanto.yourmate.domain.repository.IContactRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepository): IAuthRepository

    @Binds
    abstract fun bindChatRepository(chatRepository: ChatRepository): IChatRepository

    @Binds
    abstract fun bindContactRepository(contactRepository: ContactRepository): IContactRepository

    companion object {
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

        @Provides
        fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()
    }
}