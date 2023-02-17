package com.example.cricket.screen.seriesinfoscreen

import com.example.cricket.model.seriesinfo.SeriesInfo

data class SeriesInfoState(

    val seriesInfo: SeriesInfo? = null,
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
