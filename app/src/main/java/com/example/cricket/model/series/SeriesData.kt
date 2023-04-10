package com.example.cricket.model.series

import java.util.*

data class SeriesData(
    var endDate: String,
    var id: String,
    var matches: Int,
    var name: String,
    var odi: Int,
    var squads: Int,
    var startDate: Date?,
    var t20: Int,
    var test: Int
)