package com.example.currencies.presentation.dynamics_fragment

import com.jjoe64.graphview.series.DataPoint

data class DataPointsState(
    var isLoading: Boolean = false,
    var points: List<DataPoint> = emptyList(),
    var error: String = ""
)
