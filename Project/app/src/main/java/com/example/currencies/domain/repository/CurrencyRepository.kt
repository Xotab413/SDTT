package com.example.currencies.domain.repository

import com.example.currencies.data.remote.dto.CurrencyDto
import com.example.currencies.data.remote.dto.RateShortDto
import com.example.currencies.domain.model.Currency

interface CurrencyRepository {
    fun saveAllCurrenciesInFlow(currency: MutableList<Currency>)
    fun getAllCurrenciesFromFlow(): MutableList<Currency>
    suspend fun getCurrenciesFromApi(date: String): List<CurrencyDto>
    suspend fun getAllCurrencies(): List<Currency>
    suspend fun insert(currency: Currency)
    suspend fun getDynamics(
        id: String,
        start: String,
        end: String
    ): List<RateShortDto>
}
