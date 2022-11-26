package com.example.cricket.model.seriesinfo

data class SeriesInfo(
    val apikey: String,
    val seriesInfoData: SeriesInfoData,
    val info: InfoX,
    val status: String
)