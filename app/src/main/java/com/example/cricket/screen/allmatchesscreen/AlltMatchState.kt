package com.example.cricket.screen.allmatchesscreen

import com.example.cricket.model.allmatches.AllMatches
import com.example.cricket.model.currentmatches.CurrentMatches

data class AllMatchState(
    val allMatches: AllMatches? = null,
    val currentMatches: CurrentMatches? = null,
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
