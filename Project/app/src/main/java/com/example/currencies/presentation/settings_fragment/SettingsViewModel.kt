package com.example.currencies.presentation.settings_fragment

import androidx.lifecycle.ViewModel
import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.use_case.GetAllCurrenciesFromFlowUseCase
import com.example.currencies.domain.use_case.SaveAllCurrenciesInFlowUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel(
    private val getAllCurrenciesFromFlowUseCase: GetAllCurrenciesFromFlowUseCase,
    private val saveAllCurrenciesInFlowUseCase: SaveAllCurrenciesInFlowUseCase,
) : ViewModel() {

    val state: MutableStateFlow<MutableList<Currency>> = MutableStateFlow(arrayListOf())

    private var isInit = false
    fun setCurrencies() {
        if (!isInit) {
            state.value =
                getAllCurrenciesFromFlowUseCase().map { it.copy() } as MutableList<Currency>
            state.value.sortBy { !it.isShowing }
            isInit = true
        }
    }

    fun save() {
        saveAllCurrenciesInFlowUseCase(state.value)
    }
}
