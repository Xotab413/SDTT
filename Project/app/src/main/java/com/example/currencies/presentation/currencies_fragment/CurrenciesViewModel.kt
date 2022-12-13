package com.example.currencies.presentation.currencies_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencies.common.Constants.dateFormatForAPI
import com.example.currencies.common.Resource
import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.use_case.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*

class CurrenciesViewModel(
    private val getCurrenciesFromApiUseCase: GetCurrenciesFromApiUseCase,
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
    private val insertCurrencyUseCase: InsertCurrencyUseCase,
    private val saveAllCurrenciesInFlowUseCase: SaveAllCurrenciesInFlowUseCase,
    private val getAllCurrenciesFromFlowUseCase: GetAllCurrenciesFromFlowUseCase
) : ViewModel() {

    private val allCurrencies: MutableStateFlow<MutableList<Currency>> =
        MutableStateFlow(mutableListOf())
    private val _state = MutableStateFlow(CurrenciesState())
    var state: StateFlow<CurrenciesState> = _state

    private val calendar = Calendar.getInstance()

    // private val forTests = calendar.add(Calendar.DAY_OF_YEAR, -1)
    var todaysDate: Date = calendar.time
    private var todaysDateStr: String = dateFormatForAPI.format(calendar.time)
    private val c = calendar.add(Calendar.DAY_OF_YEAR, 1)
    var tomorrowDate: Date = calendar.time
    private var tomorrowDateStr: String = dateFormatForAPI.format(calendar.time)

    fun initCurrencies() {
        CoroutineScope(Dispatchers.IO).launch {
            _state.value = CurrenciesState(isLoading = true)
            allCurrencies.value = getAllCurrenciesFromFlowUseCase()
            allCurrencies.value = getAllCurrenciesUseCase() as MutableList<Currency>
            saveAllCurrenciesInFlowUseCase(allCurrencies.value)
        }
    }

    fun updateCurrencies() {
        if (allCurrencies.value.isNotEmpty() && allCurrencies.value[1].date == todaysDateStr) {
            allCurrencies.value = getAllCurrenciesFromFlowUseCase()
            _state.value =
                CurrenciesState(currencies = allCurrencies.value.filter { it.isShowing })
            // saveAllCurrenciesInFlowUseCase(allCurrencies.value)
        } else {
            getCurrenciesFromApiUseCase(tomorrowDateStr).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        setTodaysCurrencies(result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value =
                            CurrenciesState(
                                error = result.message ?: "An unexpected error occured",
                                currencies = allCurrencies.value.filter { it.isShowing }
                            )
                    }
                    is Resource.Loading -> {
                        _state.value = CurrenciesState(isLoading = true)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    private fun setTodaysCurrencies(tomorrowsCurrencies: List<Currency>) {
        if (tomorrowsCurrencies.isEmpty()) {
            calendar.add(Calendar.DAY_OF_YEAR, -2)

            todaysDate = calendar.time
            todaysDateStr = dateFormatForAPI.format(calendar.time)

            calendar.add(Calendar.DAY_OF_YEAR, 1)

            tomorrowDate = calendar.time
            tomorrowDateStr = dateFormatForAPI.format(calendar.time)

            updateCurrencies()
        } else {
            getCurrenciesFromApiUseCase(todaysDateStr).onEach { result ->
                when (result) {

                    is Resource.Success -> {
                        val currenciesList: List<Currency> = tomorrowsCurrencies.map { re ->
                            re.curOfficialRate = result.data?.find {
                                it.curID == re.curID
                            }?.curOfficialRate!!
                            re
                        }
                        if (allCurrencies.value.isNullOrEmpty()) {
                            allCurrencies.value = currenciesList as MutableList<Currency>
                        } else {
                            allCurrencies.value.forEach { oldCurrency ->
                                currenciesList.find { it.curID == oldCurrency.curID }?.apply {
                                    oldCurrency.curOfficialRate = curOfficialRate
                                    oldCurrency.tomorrowRate = tomorrowRate
                                }
                            }
                        }
                        allCurrencies.value.forEach { currency ->
                            currency.date = todaysDateStr
                        }
                        _state.value =
                            CurrenciesState(currencies = allCurrencies.value.filter { it.isShowing })
                        saveAllCurrenciesInFlowUseCase(allCurrencies.value)
                        saveCurrencies()
                    }

                    is Resource.Error -> {
                        _state.value =
                            CurrenciesState(
                                error = result.message ?: "An unexpected error has occurred",
                                currencies = allCurrencies.value.filter { it.isShowing }
                            )
                    }

                    is Resource.Loading -> {
                        _state.value = CurrenciesState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun saveCurrencies() {
        CoroutineScope(Dispatchers.IO).launch {
            allCurrencies.value.forEachIndexed { index, currency ->
                currency.position = index
                insertCurrencyUseCase(currency)
            }
        }
    }
}
