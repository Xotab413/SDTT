package com.example.currencies.domain.use_case

import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.repository.CurrencyRepository

class GetAllCurrenciesFromFlowUseCase(
    private val repo: CurrencyRepository
) {
    operator fun invoke(): MutableList<Currency> = repo.getAllCurrenciesFromFlow()
}
