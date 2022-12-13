package com.example.currencies.domain.use_case

import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.repository.CurrencyRepository

class InsertCurrencyUseCase(
    private val repo: CurrencyRepository
) {
    suspend operator fun invoke(currency: Currency) {
        repo.insert(currency)
    }
}
