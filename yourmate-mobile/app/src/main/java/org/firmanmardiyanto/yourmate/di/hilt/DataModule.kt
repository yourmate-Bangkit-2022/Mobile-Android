package org.firmanmardiyanto.yourmate.di.hilt

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.firmanmardiyanto.yourmate.data.api.ChatApi
import org.firmanmardiyanto.yourmate.data.repository.AuthRepository
import org.firmanmardiyanto.yourmate.data.repository.ChatRepository
import org.firmanmardiyanto.yourmate.data.repository.ContactRepository
import org.firmanmardiyanto.yourmate.di.qualifier.FirebaseCloudMessagingRetrofit
import org.firmanmardiyanto.yourmate.domain.repository.IAuthRepository
import org.firmanmardiyanto.yourmate.domain.repository.IChatRepository
import org.firmanmardiyanto.yourmate.domain.repository.IContactRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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

        @Provides
        fun provideFirebaseMessaging(): FirebaseMessaging = FirebaseMessaging.getInstance()

        @Provides
        @FirebaseCloudMessagingRetrofit
        fun provideFirebaseCloudMessagingRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/")
                .client(
                    OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .addInterceptor { chain ->
                            val requestBuilder = chain.request().newBuilder()
                            requestBuilder.addHeader(
                                "Authorization",
                                "key=AAAAPw0Urwk:APA91bGBS1AIbycSJmoIsQNvRq6gaQ0KZZFKPYUrUHzDHuPEmElNBZKac_arPW9533a9F0C2O_T4Zs3GrnJSCwDQB0zDGt-rOyRwsTYxSrtiOn0IHVpUeek0UQR4z_t8YqwFR8koj741"
                            )
                            chain.proceed(requestBuilder.build())
                        }
                        .addInterceptor(
                            HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)
                        )
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun provideChatApi(@FirebaseCloudMessagingRetrofit retrofit: Retrofit): ChatApi =
            retrofit.create(ChatApi::class.java)
    }
}