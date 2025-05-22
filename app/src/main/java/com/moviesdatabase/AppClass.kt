package com.moviesdatabase

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppClass: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppClass)
            modules(AppModule + NetworkModule+mediaModule)
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }


}