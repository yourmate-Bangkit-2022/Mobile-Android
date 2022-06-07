package org.firmanmardiyanto.yourmate

import android.app.Application
import org.firmanmardiyanto.yourmate.di.firebaseModule
import org.firmanmardiyanto.yourmate.di.repositoryModule
import org.firmanmardiyanto.yourmate.di.useCaseModule
import org.firmanmardiyanto.yourmate.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.NONE)
            modules(
                listOf(
                    firebaseModule,
                    useCaseModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}