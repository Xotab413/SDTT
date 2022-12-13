package com.example.currencies.presentation.currencies_fragment

import com.example.currencies.domain.model.Currency

data class CurrenciesState(
    var isLoading: Boolean = false,
    var currencies: List<Currency> = emptyList(),
    var error: String = ""
)
