package com.example.currencies.di

import com.example.currencies.common.Constants
import com.example.currencies.data.db.CurrencyRoomDatabase
import com.example.currencies.data.remote.NbrbApi
import com.example.currencies.data.repository.CurrencyRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * [appModule] is a Koin module that provides singleton instances of [NbrbApi] and [CurrencyRepositoryImpl] using [provideApi] function and [CurrencyRoomDatabase] class respectively.
 */
val appModule = module {
    single { provideApi() }
    single { CurrencyRepositoryImpl(get(), CurrencyRoomDatabase.getDatabase(get()).currencyDao()) }
}

/**
 * Function that creates and returns an instance of [NbrbApi] using Retrofit library.
 */
fun provideApi(): NbrbApi {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NbrbApi::class.java)
}
