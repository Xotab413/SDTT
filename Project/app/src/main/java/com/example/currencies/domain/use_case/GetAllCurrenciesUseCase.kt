package com.example.currencies.domain.use_case

import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.repository.CurrencyRepository

class GetAllCurrenciesUseCase(
    private val repo: CurrencyRepository
) {
    suspend operator fun invoke(): List<Currency> = repo.getAllCurrencies()
}
