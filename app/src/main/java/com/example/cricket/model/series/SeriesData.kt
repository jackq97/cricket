package com.example.cricket.model.series

data class SeriesData(
    var endDate: String,
    var id: String,
    var matches: Int,
    var name: String,
    var odi: Int,
    var squads: Int,
    var startDate: String,
    var t20: Int,
    var test: Int
)