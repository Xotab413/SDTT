package com.example.currencies

import android.app.Application
import com.example.currencies.di.appModule
import com.example.currencies.di.useCaseModule
import com.example.currencies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


/**
 * This is the main application class for the Currencies app.
 */
class CurrenciesApp : Application() {

    /**
     * This method is called when the application is starting up.
     * It initializes the Koin dependency injection framework with the
     * app module, use case module, and view model module.
     *
     * @see [startKoin]
     */
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CurrenciesApp)
            androidLogger(Level.ERROR)
            modules(listOf(appModule, useCaseModule, viewModelModule))
        }
    }
}
