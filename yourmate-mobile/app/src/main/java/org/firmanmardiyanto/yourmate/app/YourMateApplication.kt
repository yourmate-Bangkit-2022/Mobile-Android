package org.firmanmardiyanto.yourmate.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class YourMateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            androidContext(this@YourMateApplication)
//            androidLogger(Level.NONE)
//            modules(
//                listOf(
//                    firebaseModule,
//                    useCaseModule,
//                    repositoryModule,
//                    viewModelModule
//                )
//            )
//        }
    }
}