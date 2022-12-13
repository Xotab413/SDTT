package com.example.currencies.data.repository

import com.example.currencies.data.db.CurrencyDao
import com.example.currencies.data.remote.NbrbApi
import com.example.currencies.data.remote.dto.CurrencyDto
import com.example.currencies.data.remote.dto.RateShortDto
import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow

class CurrencyRepositoryImpl(
    private val api: NbrbApi,
    private val dao: CurrencyDao
) : CurrencyRepository {

    private val allCurrencies: MutableStateFlow<MutableList<Currency>> =
        MutableStateFlow(mutableListOf())

    override fun saveAllCurrenciesInFlow(currency: MutableList<Currency>) {
        allCurrencies.value = currency
    }

    override fun getAllCurrenciesFromFlow(): MutableList<Currency> {
        return allCurrencies.value
    }

    override suspend fun getCurrenciesFromApi(date: String): List<CurrencyDto> {
        return api.getCurrencies(date)
    }

    override suspend fun getAllCurrencies(): List<Currency> {
        return dao.getAllCurrencies()
    }

    override suspend fun insert(currency: Currency) {
        dao.insert(currency)
    }

    override suspend fun getDynamics(id: String, start: String, end: String): List<RateShortDto> {
        return api.detDynamics(id, start, end)
    }
}
