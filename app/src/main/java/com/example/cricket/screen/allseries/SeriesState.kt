package com.example.cricket.screen.allseries

import com.example.cricket.model.currentmatches.CurrentMatches
import com.example.cricket.model.series.Series

data class SeriesState(
    //val stockInfos: List<IntradayInfo> = emptyList(),
    val series: Series? = null,
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
