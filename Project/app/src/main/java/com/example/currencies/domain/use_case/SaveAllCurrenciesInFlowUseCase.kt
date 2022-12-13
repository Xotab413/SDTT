package com.example.currencies.domain.use_case

import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.repository.CurrencyRepository

class SaveAllCurrenciesInFlowUseCase(
    private val repo: CurrencyRepository
) {
    operator fun invoke(currency: MutableList<Currency>) {
        repo.saveAllCurrenciesInFlow(currency)
    }
}
