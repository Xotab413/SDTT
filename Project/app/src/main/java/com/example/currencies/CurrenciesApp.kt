package com.example.currencies

import android.app.Application
import com.example.currencies.di.appModule
import com.example.currencies.di.useCaseModule
import com.example.currencies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CurrenciesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CurrenciesApp)
            androidLogger(Level.ERROR)
            modules(listOf(appModule, useCaseModule, viewModelModule))
        }
    }
}
