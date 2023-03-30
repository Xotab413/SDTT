package com.example.currencies.di

import com.example.currencies.data.repository.CurrencyRepositoryImpl
import com.example.currencies.domain.use_case.*
import org.koin.dsl.module


/**
 * This module provides single instances of use cases that operate on the currency repository
 * to retrieve or manipulate data.
 *
 * @param [GetCurrenciesFromApiUseCase] retrieves a list of currencies from the API.
 * @param [GetAllCurrenciesUseCase] retrieves all currencies from the local database.
 * @param [InsertCurrencyUseCase] inserts a new currency into the local database.
 * @param [GetAllCurrenciesFromFlowUseCase] retrieves all currencies from the flow.
 * @param [SaveAllCurrenciesInFlowUseCase] saves all currencies to the flow.
 * @param [GetDynamicsUseCase] retrieves a list of exchange rate dynamics from the API.
 */
val useCaseModule = module {
    single { GetCurrenciesFromApiUseCase(get< CurrencyRepositoryImpl>()) }
    single { GetAllCurrenciesUseCase(get< CurrencyRepositoryImpl>()) }
    single { InsertCurrencyUseCase(get< CurrencyRepositoryImpl>()) }
    single { GetAllCurrenciesFromFlowUseCase(get< CurrencyRepositoryImpl>()) }
    single { SaveAllCurrenciesInFlowUseCase(get< CurrencyRepositoryImpl>()) }
    single { GetDynamicsUseCase(get< CurrencyRepositoryImpl>()) }
}
