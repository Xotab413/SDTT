package com.example.currencies.presentation.dynamics_fragment

import androidx.lifecycle.ViewModel
import com.example.currencies.common.Constants
import com.example.currencies.common.Resource
import com.example.currencies.domain.use_case.GetDynamicsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*

class DynamicsViewModel(
    private val getDynamicsUseCase: GetDynamicsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DataPointsState())
    var state: StateFlow<DataPointsState> = _state

    fun updateState(id: String) {
        val calendar = Calendar.getInstance()

        // private val forTests = calendar.add(Calendar.DAY_OF_YEAR, -1)
        val endDate: Date = calendar.time
        val endDateStr = Constants.dateFormatForAPI.format(endDate)
        calendar.add(Calendar.DAY_OF_YEAR, -30)
        val date: Date = calendar.time
        val startDayStr = Constants.dateFormatForAPI.format(date)
        getDynamicsUseCase(id, startDayStr, endDateStr).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = DataPointsState(points = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        DataPointsState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = DataPointsState(isLoading = true)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}
