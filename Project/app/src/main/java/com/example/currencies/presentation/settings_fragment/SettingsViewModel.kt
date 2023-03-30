package com.example.currencies.presentation.settings_fragment

import androidx.lifecycle.ViewModel
import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.use_case.GetAllCurrenciesFromFlowUseCase
import com.example.currencies.domain.use_case.SaveAllCurrenciesInFlowUseCase
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * [SettingsViewModel] is responsible for managing the state of currencies that the user has selected to display
 *
 * @property getAllCurrenciesFromFlowUseCase used to get all currencies that the user has saved to be displayed
 * @property saveAllCurrenciesInFlowUseCase used to save the updated list of currencies to be displayed
 * @property state represents the current state of the currencies list
 * @property isInit represents whether the list of currencies has been initialized
 * @constructor creates a new instance of SettingsViewModel
 */
class SettingsViewModel(
    private val getAllCurrenciesFromFlowUseCase: GetAllCurrenciesFromFlowUseCase,
    private val saveAllCurrenciesInFlowUseCase: SaveAllCurrenciesInFlowUseCase,
) : ViewModel() {

    val state: MutableStateFlow<MutableList<Currency>> = MutableStateFlow(arrayListOf())

    private var isInit = false
    /**
     * Initializes the list of currencies if it has not been initialized yet.
     */
    fun setCurrencies() {
        if (!isInit) {
            state.value =
                getAllCurrenciesFromFlowUseCase().map { it.copy() } as MutableList<Currency>
            state.value.sortBy { !it.isShowing }
            isInit = true
        }
    }

    /**
     * Saves the updated list of currencies to be displayed.
     */
    fun save() {
        saveAllCurrenciesInFlowUseCase(state.value)
    }
}
