package com.example.currencies.di

import com.example.currencies.data.repository.CurrencyRepositoryImpl
import com.example.currencies.domain.use_case.*
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCurrenciesFromApiUseCase(get< CurrencyRepositoryImpl>()) }
    single { GetAllCurrenciesUseCase(get< CurrencyRepositoryImpl>()) }
    single { InsertCurrencyUseCase(get< CurrencyRepositoryImpl>()) }
    single { GetAllCurrenciesFromFlowUseCase(get< CurrencyRepositoryImpl>()) }
    single { SaveAllCurrenciesInFlowUseCase(get< CurrencyRepositoryImpl>()) }
    single { GetDynamicsUseCase(get< CurrencyRepositoryImpl>()) }
}
