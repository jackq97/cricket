package com.example.cricket.model.seriesinfo

data class SeriesInfo(
    val apikey: String,
    val `data`: Data,
    val info: InfoX,
    val status: String
)