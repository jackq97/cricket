package com.example.cricket.screen.currentMatchesScreen

import com.example.cricket.model.currentmatches.CurrentMatches

data class CurrentMatchState(
    //val stockInfos: List<IntradayInfo> = emptyList(),
    val currentMatches: CurrentMatches? = null,
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
