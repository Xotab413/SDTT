package com.example.currencies.data.repository

import com.example.currencies.data.db.CurrencyDao
import com.example.currencies.data.remote.NbrbApi
import com.example.currencies.data.remote.dto.CurrencyDto
import com.example.currencies.data.remote.dto.RateShortDto
import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * An implementation of [CurrencyRepository] interface
 * which defines the methods for getting currency data from the API and the local database.
 */
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

    /**
     * Retrieves a list of currencies from the API for a specified date.
     * @param date the date for which to retrieve currency data in the format "yyyy-MM-dd"
     * @return a list of [CurrencyDto] objects representing the retrieved currencies
     */
    override suspend fun getCurrenciesFromApi(date: String): List<CurrencyDto> {
        return api.getCurrencies(date)
    }

    /**
     * Invoke DAO getAllCurrencies() method
     */
    override suspend fun getAllCurrencies(): List<Currency> {
        return dao.getAllCurrencies()
    }

    /**
     * Invoke DAO insert method
     */
    override suspend fun insert(currency: Currency) {
        dao.insert(currency)
    }

    /**
     * Get Dynamically from API method
     * @return List of [RateShortDto]
     */
    override suspend fun getDynamics(id: String, start: String, end: String): List<RateShortDto> {
        return api.detDynamics(id, start, end)
    }
}
