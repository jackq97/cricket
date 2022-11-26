package com.example.cricket.model.series

data class Series(
    val apikey: String,
    val `data`: List<SeriesData>,
    val info: Info,
    val status: String
)